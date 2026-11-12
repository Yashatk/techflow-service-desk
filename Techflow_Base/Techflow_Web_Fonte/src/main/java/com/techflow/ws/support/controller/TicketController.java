package com.techflow.ws.support.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.ws.support.dto.TicketDTO;
import com.techflow.ws.support.dto.TicketDetailDTO;
import com.techflow.ws.support.service.TicketService;
import com.techflow.ws.sys.domain.ServiceResponse;




@RestController
@RequestMapping("/api/support/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping
    public ResponseEntity<ServiceResponse<List<TicketDTO>>> findBy(
        @RequestParam(required=false) Integer ticketId,
        @RequestParam(required=false) String key,
        @RequestParam(required=false) Integer statusId,
        @RequestParam(required=false) Integer categoryId,
        @RequestParam(required=false) Integer priorityId,
        @RequestParam(required=false) Integer userId,
        @RequestParam(required=false) String startDate,
        @RequestParam(required=false) String endDate,
        @RequestParam(required=false, defaultValue = "1") Integer page,
        @RequestParam(required=false, defaultValue = "100") Integer pageSize
        ) {
        ServiceResponse<List<TicketDTO>> resp = ticketService.findBy(ticketId, key, statusId, categoryId, priorityId, userId, startDate, endDate, page, pageSize);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<TicketDTO>> create(@RequestBody TicketDTO ticket) {
        ServiceResponse<TicketDTO> resp = ticketService.create(ticket);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<TicketDTO>> update(@PathVariable Integer id, @RequestBody TicketDTO ticket) {
        ServiceResponse<TicketDTO> resp = ticketService.update(id, ticket);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }
    
    @PostMapping("/detail")
    public ResponseEntity<ServiceResponse<TicketDTO>> addDetail(@RequestBody TicketDetailDTO detail) {
        ServiceResponse<TicketDTO> resp = ticketService.addDetail(detail);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }

    @PutMapping("/detail/{id}")
    public ResponseEntity<ServiceResponse<TicketDTO>> updateDetail(@PathVariable Integer id, @RequestBody TicketDetailDTO detail) {
        ServiceResponse<TicketDTO> resp = ticketService.updateDetail(id, detail);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }

}
