package com.techflow.ws.support.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.techflow.ws.support.dto.TicketStatusDTO;
import com.techflow.ws.support.service.TicketStatusService;
import com.techflow.ws.sys.domain.ServiceResponse;


@RestController
public class TicketStatusController {

    @Autowired
    TicketStatusService ticketStatusService;

    @GetMapping("api/support/ticket/status")
    public ResponseEntity<ServiceResponse<List<TicketStatusDTO>>> findBy() {
        ServiceResponse<List<TicketStatusDTO>> resp = ticketStatusService.findBy();
        return new ResponseEntity<>(resp, resp.getHttpStatus());

    }
    
}
