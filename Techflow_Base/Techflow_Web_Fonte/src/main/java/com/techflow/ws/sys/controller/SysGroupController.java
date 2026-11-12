package com.techflow.ws.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.ws.sys.domain.ServiceResponse;
import com.techflow.ws.sys.dto.SysGroupDTO;
import com.techflow.ws.sys.service.SysGroupService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/sys/group")
public class SysGroupController {

    @Autowired
    SysGroupService sysGroupService;

    @GetMapping
    public ResponseEntity<ServiceResponse<List<SysGroupDTO>>> findBy() {
        ServiceResponse<List<SysGroupDTO>> response = sysGroupService.findBy();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<SysGroupDTO>> createGroup(@RequestBody SysGroupDTO groupDTO) {
        ServiceResponse<SysGroupDTO> response = sysGroupService.create(groupDTO);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

     @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<SysGroupDTO>> updateCategory(@PathVariable Integer id, @RequestBody SysGroupDTO dto) {
        ServiceResponse<SysGroupDTO> resp = sysGroupService.update(id, dto);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }
     
    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<String>> deleteCategory(@PathVariable Integer id) {
        ServiceResponse<String> resp = sysGroupService.delete(id);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }
    
    
    
}
