package com.techflow.ws.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.ws.sys.domain.ServiceResponse;
import com.techflow.ws.sys.dto.SysLoginDTO;
import com.techflow.ws.sys.service.SysLoginService;


@RestController
public class LoginController {

    @Autowired
    private SysLoginService loginService;

    @GetMapping("api/login")
    public ResponseEntity<ServiceResponse<SysLoginDTO>> login(@RequestParam String username, @RequestParam String password) {
        ServiceResponse<SysLoginDTO> resp = loginService.login(username, password);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }
    
    @GetMapping("api/logout")
    public ResponseEntity<ServiceResponse<String>> logout(@RequestParam Integer uid) {
        ServiceResponse<String> resp = loginService.logout(uid);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }
    
}
