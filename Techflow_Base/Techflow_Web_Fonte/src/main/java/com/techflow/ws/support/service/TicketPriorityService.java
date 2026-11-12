package com.techflow.ws.support.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techflow.ws.support.converter.TicketPriorityConverter;
import com.techflow.ws.support.dto.TicketPriorityDTO;
import com.techflow.ws.support.entity.TicketPriority;
import com.techflow.ws.support.entity.TicketPriorityEnum;
import com.techflow.ws.support.repository.TicketPriorityRepository;
import com.techflow.ws.sys.domain.ServiceResponse;

import jakarta.annotation.PostConstruct;

@Service
public class TicketPriorityService {

    @Autowired
    TicketPriorityRepository ticketPriorityRepository;

    public static Logger log = LogManager.getLogger(TicketPriorityService.class);


    @PostConstruct
    public void init() {
        for(TicketPriorityEnum item : TicketPriorityEnum.values()) {
            if(!ticketPriorityRepository.existsById(item.getId())) {
                var entity = new TicketPriority();
                entity.setId(item.getId());
                entity.setPriority(item.name());
                ticketPriorityRepository.save(entity);
            }
        }
    }

    public ServiceResponse<List<TicketPriorityDTO>> findBy() {
        ServiceResponse<List<TicketPriorityDTO>> resp = new ServiceResponse<>();
        try {
            List<TicketPriority> entities = ticketPriorityRepository.findAll();
            resp.setObj(TicketPriorityConverter.convertToDTO(entities));
            resp.setOK();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }   
        return resp;
    }
}
