package com.techflow.ws.support.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.ws.support.dto.TicketPriorityDTO;
import com.techflow.ws.support.service.TicketPriorityService;
import com.techflow.ws.sys.domain.ServiceResponse;


@RestController
public class TicketPriorityController {

    @Autowired
    private TicketPriorityService ticketPriorityService;

    @GetMapping("api/support/ticket/priority")
    public ResponseEntity<ServiceResponse<List<TicketPriorityDTO>>> findBy() {
        ServiceResponse<List<TicketPriorityDTO>> resp = ticketPriorityService.findBy();
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }
    
}
