package com.techflow.ws.sys.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.techflow.ws.sys.domain.EncryptUtils;
import com.techflow.ws.sys.domain.ServiceResponse;
import com.techflow.ws.sys.dto.SysLoginDTO;
import com.techflow.ws.sys.entity.SysUser;
import com.techflow.ws.sys.repository.SysHistoryRepository;
import com.techflow.ws.sys.repository.SysUserRepository;

class SysLoginServiceTest {

    private final PasswordEncoder encoder = new BCryptPasswordEncoder(4); // low cost keeps tests fast
    private SysUserRepository users;
    private SysLoginService service;

    @BeforeEach
    void setUp() {
        users = mock(SysUserRepository.class);
        service = new SysLoginService(users, mock(SysHistoryRepository.class), encoder);
    }

    private SysUser user(String storedHash) {
        SysUser u = new SysUser("demo", storedHash, "Usuário Demo", "demo@techflow.example");
        u.setId(2);
        when(users.findByUsernameAndSt("demo", 1)).thenReturn(u);
        return u;
    }

    @Test
    void acceptsBcryptPasswordAndIssuesToken() {
        user(encoder.encode("demo123"));
        ServiceResponse<SysLoginDTO> resp = service.login("demo", "demo123");
        assertEquals(1, resp.getCode());
        assertNotNull(resp.getObj().getToken());
    }

    @Test
    void migratesLegacyMd5HashToBcryptOnSuccessfulLogin() {
        SysUser u = user(EncryptUtils.md5("demo123"));
        assertEquals(1, service.login("demo", "demo123").getCode());
        assertTrue(u.getPassword().startsWith("$2"), "hash should now be BCrypt");
        assertTrue(encoder.matches("demo123", u.getPassword()));
        verify(users).save(u);
    }

    @Test
    void rejectsWrongPasswordWithoutTouchingTheHash() {
        SysUser u = user(EncryptUtils.md5("demo123"));
        String before = u.getPassword();
        assertEquals(0, service.login("demo", "wrong").getCode());
        assertEquals(before, u.getPassword());
        verify(users, never()).save(any());
    }

    @Test
    void rejectsUnknownUser() {
        assertEquals(0, service.login("nobody", "x").getCode());
    }
}
