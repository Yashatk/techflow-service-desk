package com.techflow.ws.sys.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.ws.sys.domain.ServiceResponse;
import com.techflow.ws.sys.dto.LoginRequestDTO;
import com.techflow.ws.sys.dto.SysLoginDTO;
import com.techflow.ws.sys.service.SysLoginService;


@RestController
public class LoginController {

    private final SysLoginService loginService;

    public LoginController(SysLoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("api/login")
    public ResponseEntity<ServiceResponse<SysLoginDTO>> login(@RequestBody LoginRequestDTO request) {
        if (request == null || request.username() == null || request.password() == null) {
            ServiceResponse<SysLoginDTO> invalid = new ServiceResponse<>();
            invalid.setMessage("Usuário e senha são obrigatórios.");
            return new ResponseEntity<>(invalid, invalid.getHttpStatus());
        }
        ServiceResponse<SysLoginDTO> resp = loginService.login(request.username(), request.password());
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }
    
    @GetMapping("api/logout")
    public ResponseEntity<ServiceResponse<String>> logout(@RequestParam Integer uid) {
        ServiceResponse<String> resp = loginService.logout(uid);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }
    
}
