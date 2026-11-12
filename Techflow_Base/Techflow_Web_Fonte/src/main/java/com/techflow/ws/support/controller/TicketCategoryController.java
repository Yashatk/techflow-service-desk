package com.techflow.ws.support.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.ws.support.dto.TicketCategoryDTO;
import com.techflow.ws.support.service.TicketCategoryService;
import com.techflow.ws.sys.domain.ServiceResponse;

@RestController
@RequestMapping("/api/support/ticket/category")
public class TicketCategoryController {

    @Autowired
    private TicketCategoryService ticketCategoryService;

    @GetMapping
    public ResponseEntity<ServiceResponse<List<TicketCategoryDTO>>> findBy() {
        ServiceResponse<List<TicketCategoryDTO>> resp = ticketCategoryService.findBy();
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<TicketCategoryDTO>> createCategory(@RequestBody TicketCategoryDTO dto) {
        ServiceResponse<TicketCategoryDTO> resp = ticketCategoryService.create(dto);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<TicketCategoryDTO>> updateCategory(@PathVariable Integer id, @RequestBody TicketCategoryDTO dto) {
        ServiceResponse<TicketCategoryDTO> resp = ticketCategoryService.update(id, dto);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }
     
    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<String>> deleteCategory(@PathVariable Integer id) {
        ServiceResponse<String> resp = ticketCategoryService.delete(id);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }
}
