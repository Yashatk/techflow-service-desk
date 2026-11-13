package com.techflow.ws.sys.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techflow.ws.sys.converter.SysUserConverter;
import com.techflow.ws.sys.domain.ServiceResponse;
import com.techflow.ws.sys.dto.SysUserDTO;
import com.techflow.ws.sys.entity.SysAccess;
import com.techflow.ws.sys.entity.SysAccessEnum;
import com.techflow.ws.sys.entity.SysGroup;
import com.techflow.ws.sys.entity.SysGroupAccess;
import com.techflow.ws.sys.entity.SysGroupAccessId;
import com.techflow.ws.sys.entity.SysGroupUser;
import com.techflow.ws.sys.entity.SysGroupUserId;
import com.techflow.ws.sys.entity.SysUser;
import com.techflow.ws.sys.repository.SysAccessRepository;
import com.techflow.ws.sys.repository.SysGroupAccessRepository;
import com.techflow.ws.sys.repository.SysGroupRepository;
import com.techflow.ws.sys.repository.SysGroupUserRepository;
import com.techflow.ws.sys.repository.SysHistoryRepository;
import com.techflow.ws.sys.repository.SysUserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class SysUserService implements UserDetailsService {

    private final SysUserRepository sysUserRepository;

    private final SysGroupRepository sysGroupRepository;

    private final SysGroupUserRepository sysGroupUserRepository;

    private final SysGroupAccessRepository sysGroupAccessRepository;

    private final SysAccessRepository sysAccessRepository;

    private final SysHistoryRepository sysHistoryRepository;

    private final PasswordEncoder passwordEncoder;

    public SysUserService(SysUserRepository sysUserRepository, SysGroupRepository sysGroupRepository, SysGroupUserRepository sysGroupUserRepository, SysGroupAccessRepository sysGroupAccessRepository, SysAccessRepository sysAccessRepository, SysHistoryRepository sysHistoryRepository, PasswordEncoder passwordEncoder) {
        this.sysUserRepository = sysUserRepository;
        this.sysGroupRepository = sysGroupRepository;
        this.sysGroupUserRepository = sysGroupUserRepository;
        this.sysGroupAccessRepository = sysGroupAccessRepository;
        this.sysAccessRepository = sysAccessRepository;
        this.sysHistoryRepository = sysHistoryRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private static final Logger log = LogManager.getLogger(SysUserService.class);

    public static final String ADMIN_GROUP = "Administrador";

    /** The authenticated caller, taken from the validated JWT; never from the request body. */
    private SysUser currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth == null ? null : sysUserRepository.findByUsername(auth.getName());
    }

    private static boolean isAdmin(SysUser user) {
        return user != null && user.getGroups().stream().anyMatch(g -> ADMIN_GROUP.equals(g.getGroupName()));
    }

    private static <T> ServiceResponse<T> forbidden(ServiceResponse<T> response) {
        response.setError();
        response.setHttpStatus(HttpStatus.FORBIDDEN);
        response.setMessage("Acesso negado.");
        return response;
    }

    @PostConstruct
    public void init() {
        // Default admin only when ADMIN_DEFAULT_PASSWORD is provided; no credential lives in the code.
        String adminPassword = System.getenv("ADMIN_DEFAULT_PASSWORD");
        if(adminPassword != null && !adminPassword.isBlank() && !sysUserRepository.existsById(1)) {
            sysUserRepository.save(new SysUser("admin", passwordEncoder.encode(adminPassword), "Administrador", "admin@example.com"));
        }
        // Cria grupos padrão se não existirem
        List<SysGroup> groups = new ArrayList<>();
        groups.add(new SysGroup(ADMIN_GROUP, "Administradores do sistema"));
        groups.add(new SysGroup("Analistas", "Analistas de suporte"));
        groups.add(new SysGroup("Clientes", "Clientes"));
        for(SysGroup group : groups) {
            if(!sysGroupRepository.existsByGroupName(group.getGroupName())) {
                sysGroupRepository.save(group);
            }
        }
        // Grupo Administrador e Usuário Administrador
        SysGroup adminGroup = sysGroupRepository.findByGroupName(ADMIN_GROUP);
        SysUser adminUser = sysUserRepository.findByUsername("admin");

        // Cria acessos padrão se não existirem
        for(SysAccessEnum item : SysAccessEnum.values()) {
            SysAccess access = sysAccessRepository.findById(item.name()).orElse(null);
            if(access == null) {
                access = sysAccessRepository.save(new SysAccess(item.name(),item.getDescricao()));                
            }
            if(access != null) {
                // Vincula acesso ao grupo Administrador
                sysGroupAccessRepository.save(new SysGroupAccess(new SysGroupAccessId(adminGroup, access)));
            }
        }
        
        // Vincula usuário admin ao grupo Administrador
        if(adminUser != null && !sysGroupUserRepository.existsById(new SysGroupUserId(adminGroup,adminUser))) {
            sysGroupUserRepository.save(new SysGroupUser(new SysGroupUserId(adminGroup, adminUser)));
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER"))
        );
    }

    public ServiceResponse<List<SysUserDTO>> getUser(Integer uid) {
        ServiceResponse<List<SysUserDTO>> response = new ServiceResponse<>();
        SysUser caller = currentUser();
        // Deny by default: only admins list or read other users; everyone else reads only their own profile.
        if (caller == null || (!isAdmin(caller) && (uid == null || !uid.equals(caller.getId())))) {
            return forbidden(response);
        }
        try {
            List<SysUser> users;
            if(uid == null || uid < 0)
                users = sysUserRepository.findAll();
            else {
                users = sysUserRepository.findById(uid)
                        .map(List::of)
                        .orElse(new ArrayList<>());
            }
            if(users.isEmpty()) {
                response.setMessage("Nenhum usuário encontrado.");
                return response;
            }
            // Traz com os acessos
            response.setObj(SysUserConverter.convertToDTO(users, true, true, false));
            response.setOK();
        }
        catch (Exception e) {
            response.setMessage("Erro ao buscar usuário: " + e.getMessage());
        }
        return response;
    }

    public ServiceResponse<SysUserDTO> createUser(SysUserDTO dto) {
        ServiceResponse<SysUserDTO> response = new ServiceResponse<>();
        try {
            if(dto.getId() != null && dto.getId() > 0 ) {
                SysUser existingUser = sysUserRepository.findById(dto.getId()).orElse(null);
                if(existingUser == null) {
                    response.setMessage("Usuário não encontrado para atualização.");
                    return response;
                }
                // Atualiza os campos
                existingUser.setName(dto.getName());
                existingUser.setEmail(dto.getEmail());
                SysUser updatedUser = sysUserRepository.save(existingUser);
                response.setObj(SysUserConverter.convertToDTO(updatedUser, true, true, false));
                response.setOK();
            }
            else {
                // Verifica se o username já existe
                if(sysUserRepository.existsByUsernameIgnoreCase(dto.getUsername())) {
                    response.setMessage("Username já existe.");
                    return response;
                }
                SysUser user = SysUserConverter.convertTo(dto);
                user.setPassword(passwordEncoder.encode(user.getPassword()));            
                user.setSid(java.util.UUID.randomUUID().toString().replace("-", ""));
                SysUser savedUser = sysUserRepository.save(user);
                response.setObj(SysUserConverter.convertToDTO(savedUser, true, true, false));
                response.setOK();
            }
        }
        catch (Exception e) {
            response.setMessage("Erro ao criar usuário: " + e.getMessage());
        }
        return response;
    }

    public ServiceResponse<String> deleteUser(Integer uid) {
        ServiceResponse<String> response = new ServiceResponse<>();
        try {
            if(!sysUserRepository.existsById(uid)) {
                response.setMessage("Usuário não encontrado.");
                return response;
            }
            sysUserRepository.deleteById(uid);
            response.setObj("Usuário deletado com sucesso.");
            response.setOK();
        }
        catch (Exception e) {
            response.setMessage("Erro ao deletar usuário: " + e.getMessage());
        }
        return response;
    }

    public ServiceResponse<SysUserDTO> updateUser(Integer uid, SysUserDTO user) {
        ServiceResponse<SysUserDTO> response = new ServiceResponse<>();
        try {            
            SysUser existingUser = sysUserRepository.findById(uid).orElse(null);
            if(existingUser == null) {
                response.setMessage("Usuário não encontrado.");
                return response;
            }
            // Atualiza os campos
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            if(user.getPassword() != null && !user.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            SysUser updatedUser = sysUserRepository.save(existingUser);
            response.setObj(SysUserConverter.convertToDTO(updatedUser, true, true, false));
            response.setOK();
        }
        catch (Exception e) {
            response.setMessage("Erro ao atualizar usuário: " + e.getMessage());
        }
        return response;
    }

    public ServiceResponse<String> changePassword(SysUserDTO user) {
        ServiceResponse<String> response = new ServiceResponse<>();
        SysUser caller = currentUser();
        if (caller == null || user == null || user.getId() == null) {
            return forbidden(response);
        }
        boolean self = user.getId().equals(caller.getId());
        // Admins may reset other accounts; changing your own password always requires the current one.
        if (!self && !isAdmin(caller)) {
            return forbidden(response);
        }
        try {
            SysUser existingUser = sysUserRepository.findById(user.getId()).orElse(null);
            if(existingUser == null) {
                response.setMessage("Usuário não encontrado.");
                return response;
            }
            if (self && (user.getCurrentPassword() == null
                    || !passwordEncoder.matches(user.getCurrentPassword(), existingUser.getPassword()))) {
                return forbidden(response);
            }
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            sysUserRepository.save(existingUser);
            response.setObj("Senha alterada com sucesso.");
            response.setOK();
            sysHistoryRepository.save(new com.techflow.ws.sys.entity.SysHistory(com.techflow.ws.sys.entity.SysHistoryTypeEnum.SISTEMA.name(), "Senha alterada para o usuário: " + existingUser.getUsername()));            
        }
        catch (Exception e) {
            response.setMessage("Erro ao alterar senha: " + e.getMessage());
        }
        return response;
    }

    public ServiceResponse<String> changeStatus(Integer uid) {
        ServiceResponse<String> response = new ServiceResponse<>();
        try {
            SysUser existingUser = sysUserRepository.findById(uid).orElse(null);
            if(existingUser == null) {
                response.setMessage("Usuário não encontrado.");
                return response;
            }
            int newStatus = (existingUser.getSt() == 1) ? 0 : 1;
            existingUser.setSt(newStatus);
            sysUserRepository.save(existingUser);
            response.setObj("Status alterado com sucesso.");
            response.setOK();
            sysHistoryRepository.save(new com.techflow.ws.sys.entity.SysHistory(com.techflow.ws.sys.entity.SysHistoryTypeEnum.SISTEMA.name(), "Status alterado para o usuário: " + existingUser.getUsername()));            
        }
        catch (Exception e) {
            response.setMessage("Erro ao alterar status: " + e.getMessage());
        }
        return response;
    }
}
