package com.techflow.ws.sys.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.ws.sys.domain.ServiceResponse;
import com.techflow.ws.sys.dto.IdValueDTO;
import com.techflow.ws.sys.dto.SysHistoryDTO;
import com.techflow.ws.sys.service.SysHistoryService;


@RestController
@RequestMapping("/api/sys/history")
public class SysHistoryController {

    @Autowired
    private SysHistoryService sysHistoryService;

    @GetMapping
    public ResponseEntity<ServiceResponse<List<SysHistoryDTO>>> findBy(
        @RequestParam(required = false) String historyTypeId,
        @RequestParam(required = false) String key,        
        @RequestParam(required = false) String startDate,
        @RequestParam(required = false) String endDate,
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer pageSize
    ) {
        ServiceResponse<List<SysHistoryDTO>> resp = sysHistoryService.findBy(historyTypeId, key, startDate, endDate, page, pageSize);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }
    
    @GetMapping("/type")
    public ResponseEntity<ServiceResponse<List<IdValueDTO>>> findTypeBy() {
        ServiceResponse<List<IdValueDTO>> resp = sysHistoryService.findTypeBy();
        return new ResponseEntity<>(resp, resp.getHttpStatus());        
    }
    
}
