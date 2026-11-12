package com.techflow.ws.sys.service;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techflow.ws.config.JwtAuthFilter;
import com.techflow.ws.sys.domain.EncryptUtils;
import com.techflow.ws.sys.domain.ServiceResponse;
import com.techflow.ws.sys.dto.SysLoginDTO;
import com.techflow.ws.sys.entity.SysHistory;
import com.techflow.ws.sys.entity.SysHistoryTypeEnum;
import com.techflow.ws.sys.entity.SysUser;
import com.techflow.ws.sys.repository.SysHistoryRepository;
import com.techflow.ws.sys.repository.SysUserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SysLoginService {

    // Use a strong secret key in production and store it securely!
    private static final long JWT_EXPIRATION_MS = 24 * 60 * 60 * 1000; // 24 hours

    private final SysUserRepository sysUserRepository;

    private final SysHistoryRepository sysHistoryRepository;

    private final PasswordEncoder passwordEncoder;

    public SysLoginService(SysUserRepository sysUserRepository, SysHistoryRepository sysHistoryRepository, PasswordEncoder passwordEncoder) {
        this.sysUserRepository = sysUserRepository;
        this.sysHistoryRepository = sysHistoryRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static final Logger log = LogManager.getLogger(SysLoginService.class);

    public ServiceResponse<SysLoginDTO> login(String username, String password) {
        ServiceResponse<SysLoginDTO> resp = new ServiceResponse<>();
        try {
            SysUser user = sysUserRepository.findByUsernameAndSt(username.trim().toLowerCase(),1);

            if(user != null && passwordMatches(user, password)) {
                // Altera sessão
                user.setSid(java.util.UUID.randomUUID().toString().replace("-", ""));
                sysUserRepository.save(user);

                // Adiciona histórico
                sysHistoryRepository.save(new SysHistory(SysHistoryTypeEnum.LOGIN.name(), "Login com sucesso para o usuário: " + username));

                SysLoginDTO dto = new SysLoginDTO();
                dto.setUserId(user.getId());
                dto.setUsername(user.getUsername());
                dto.setName(user.getName());
                dto.setEmail(user.getEmail());

                // JWT Token generation
                String token = Jwts.builder()
                        .setSubject(user.getUsername())
                        .claim("userId", user.getId())
                        .claim("email", user.getEmail())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                        .signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(JwtAuthFilter.JWT_SECRET.getBytes()), SignatureAlgorithm.HS256)
                        .compact();
                dto.setToken(token);
                resp.setObj(dto);
                resp.setOK();
            } else {
                sysHistoryRepository.save(new SysHistory(SysHistoryTypeEnum.LOGIN.name(), "Falha no login para o usuário: " + username));
                resp.setMessage("Usuário ou senha inválidos.");
            }
        } catch(Exception e) {
            log.error("Erro ao autenticar usuário.", e);
            resp.setMessage("Erro ao autenticar usuário: " + e.getMessage());
        }
        return resp;
    }

    /**
     * BCrypt check. Accounts still holding a legacy MD5 hash are verified once and
     * transparently re-hashed with BCrypt (persisted by the caller's save).
     */
    private boolean passwordMatches(SysUser user, String rawPassword) {
        String stored = user.getPassword();
        if (stored == null || rawPassword == null) return false;
        if (stored.startsWith("$2")) {
            return passwordEncoder.matches(rawPassword, stored);
        }
        if (stored.equalsIgnoreCase(EncryptUtils.md5(rawPassword))) { // NOSONAR legacy hash, verified only to migrate it
            user.setPassword(passwordEncoder.encode(rawPassword));
            return true;
        }
        return false;
    }

    public ServiceResponse<String> logout(Integer uid) {
        ServiceResponse<String> resp = new ServiceResponse<>();
        try {
            resp.setMessage("Usuário desabilitado.");
            resp.setOK();
        }
        catch(Exception e) {
            log.error("Erro ao fazer logout do usuário.", e);
            resp.setMessage("Erro ao fazer logout do usuário: " + e.getMessage());
        }
        return resp;
    }


}
