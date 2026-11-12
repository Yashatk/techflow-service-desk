package com.techflow.ws.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.ws.sys.domain.ServiceResponse;
import com.techflow.ws.sys.dto.SysUserDTO;
import com.techflow.ws.sys.service.SysUserService;


// Usuários - CRUD - CONTROLLER
@RestController
@RequestMapping("/api/sys/user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    // CRUD - CREATE
    @PostMapping
    public ResponseEntity<ServiceResponse<SysUserDTO>> createUser(@RequestBody SysUserDTO user) {
        ServiceResponse<SysUserDTO> response = sysUserService.createUser(user);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
    
    // CRUD - READ
    @GetMapping
    public ResponseEntity<ServiceResponse<List<SysUserDTO>>> getUser(@RequestParam(required=false) Integer uid) {        
        ServiceResponse<List<SysUserDTO>> response = sysUserService.getUser(uid);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    // CRUD - UPDATE
    @PatchMapping("/{uid}")
    public ResponseEntity<ServiceResponse<SysUserDTO>> updateUser(@PathVariable Integer uid, @RequestBody SysUserDTO user) {
        ServiceResponse<SysUserDTO> response = sysUserService.updateUser(uid, user);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    // CRUD - DELETE
    @DeleteMapping("/{uid}")
    public ResponseEntity<ServiceResponse<String>> deleteUser(@PathVariable Integer uid) {
        ServiceResponse<String> response = sysUserService.deleteUser(uid);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    // CRUD - UPDATE
    @PatchMapping("/changepassword")
    public ResponseEntity<ServiceResponse<String>> changePassword(@RequestBody SysUserDTO user) {
        ServiceResponse<String> response = sysUserService.changePassword(user);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PatchMapping("/changestatus/{uid}")
    public ResponseEntity<ServiceResponse<String>> changeStatus(@PathVariable Integer uid) {
        ServiceResponse<String> response = sysUserService.changeStatus(uid);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
    
}
