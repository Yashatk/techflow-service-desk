package com.techflow.ws.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.ws.sys.domain.ServiceResponse;
import com.techflow.ws.sys.dto.SysAccessDTO;
import com.techflow.ws.sys.service.SysAccessService;


@RestController
@RequestMapping("/api/sys/access")
public class SysAccessController {

    @Autowired
    SysAccessService sysAccessService;

    @GetMapping
    public ResponseEntity<ServiceResponse<List<SysAccessDTO>>> findBy() {
        ServiceResponse<List<SysAccessDTO>> response = sysAccessService.findBy();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
    
    
}
