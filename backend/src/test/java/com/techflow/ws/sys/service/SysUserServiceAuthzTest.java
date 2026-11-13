package com.techflow.ws.sys.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.techflow.ws.sys.domain.ServiceResponse;
import com.techflow.ws.sys.dto.SysUserDTO;
import com.techflow.ws.sys.entity.SysGroup;
import com.techflow.ws.sys.entity.SysUser;
import com.techflow.ws.sys.repository.SysAccessRepository;
import com.techflow.ws.sys.repository.SysGroupAccessRepository;
import com.techflow.ws.sys.repository.SysGroupRepository;
import com.techflow.ws.sys.repository.SysGroupUserRepository;
import com.techflow.ws.sys.repository.SysHistoryRepository;
import com.techflow.ws.sys.repository.SysUserRepository;

/** Regression tests: a regular user must not take over or list other accounts. */
class SysUserServiceAuthzTest {

    private final PasswordEncoder encoder = new BCryptPasswordEncoder(4);
    private SysUserRepository users;
    private SysUserService service;
    private SysUser admin;
    private SysUser demo;

    @BeforeEach
    void setUp() {
        users = mock(SysUserRepository.class);
        service = new SysUserService(users, mock(SysGroupRepository.class), mock(SysGroupUserRepository.class),
                mock(SysGroupAccessRepository.class), mock(SysAccessRepository.class),
                mock(SysHistoryRepository.class), encoder);

        admin = new SysUser("admin", encoder.encode("admin-pass"), "Administrador", "admin@example.com");
        admin.setId(1);
        admin.addGroup(new SysGroup(SysUserService.ADMIN_GROUP, "Administradores do sistema"));
        demo = new SysUser("demo", encoder.encode("demo123"), "Usuário Demo", "demo@techflow.example");
        demo.setId(2);

        when(users.findByUsername("admin")).thenReturn(admin);
        when(users.findByUsername("demo")).thenReturn(demo);
        when(users.findById(1)).thenReturn(Optional.of(admin));
        when(users.findById(2)).thenReturn(Optional.of(demo));
        when(users.findAll()).thenReturn(List.of(admin, demo));
    }

    @AfterEach
    void clearContext() {
        SecurityContextHolder.clearContext();
    }

    private static void loginAs(String username) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(username, null, List.of()));
    }

    private static SysUserDTO passwordChange(int id, String newPassword, String currentPassword) {
        SysUserDTO dto = new SysUserDTO();
        dto.setId(id);
        dto.setPassword(newPassword);
        dto.setCurrentPassword(currentPassword);
        return dto;
    }

    @Test
    void regularUserCannotChangeAnotherUsersPassword() {
        loginAs("demo");
        ServiceResponse<String> resp = service.changePassword(passwordChange(1, "hijacked", null));
        assertEquals(HttpStatus.FORBIDDEN, resp.getHttpStatus());
        assertTrue(encoder.matches("admin-pass", admin.getPassword()), "admin password must be unchanged");
        verify(users, never()).save(any());
    }

    @Test
    void ownPasswordChangeRequiresCurrentPassword() {
        loginAs("demo");
        ServiceResponse<String> resp = service.changePassword(passwordChange(2, "new-pass", "wrong"));
        assertEquals(HttpStatus.FORBIDDEN, resp.getHttpStatus());
        verify(users, never()).save(any());
    }

    @Test
    void userChangesOwnPasswordWithCurrentPassword() {
        loginAs("demo");
        ServiceResponse<String> resp = service.changePassword(passwordChange(2, "new-pass", "demo123"));
        assertEquals(HttpStatus.OK, resp.getHttpStatus());
        assertTrue(encoder.matches("new-pass", demo.getPassword()));
    }

    @Test
    void adminCanResetAnotherUsersPassword() {
        loginAs("admin");
        ServiceResponse<String> resp = service.changePassword(passwordChange(2, "reset-pass", null));
        assertEquals(HttpStatus.OK, resp.getHttpStatus());
        assertTrue(encoder.matches("reset-pass", demo.getPassword()));
    }

    @Test
    void unauthenticatedPasswordChangeIsDenied() {
        ServiceResponse<String> resp = service.changePassword(passwordChange(2, "x", "demo123"));
        assertEquals(HttpStatus.FORBIDDEN, resp.getHttpStatus());
        verify(users, never()).save(any());
    }

    @Test
    void regularUserCannotListAllUsers() {
        loginAs("demo");
        assertEquals(HttpStatus.FORBIDDEN, service.getUser(null).getHttpStatus());
        assertEquals(HttpStatus.FORBIDDEN, service.getUser(1).getHttpStatus());
    }

    @Test
    void regularUserCanReadOwnProfile() {
        loginAs("demo");
        ServiceResponse<List<SysUserDTO>> resp = service.getUser(2);
        assertEquals(HttpStatus.OK, resp.getHttpStatus());
        assertEquals(1, resp.getObj().size());
        assertEquals(2, resp.getObj().get(0).getId());
    }

    @Test
    void adminCanListAllUsers() {
        loginAs("admin");
        ServiceResponse<List<SysUserDTO>> resp = service.getUser(null);
        assertEquals(HttpStatus.OK, resp.getHttpStatus());
        assertEquals(2, resp.getObj().size());
    }
}
