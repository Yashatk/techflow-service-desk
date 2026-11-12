package com.techflow.ws.support.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techflow.ws.support.converter.TicketStatusConverter;
import com.techflow.ws.support.dto.TicketStatusDTO;
import com.techflow.ws.support.entity.TicketStatus;
import com.techflow.ws.support.entity.TicketStatusEnum;
import com.techflow.ws.support.repository.TicketStatusRepository;
import com.techflow.ws.sys.domain.ServiceResponse;

import jakarta.annotation.PostConstruct;

@Service
public class TicketStatusService {

    @Autowired
    private TicketStatusRepository ticketStatusRepository;

    private static Logger log = LogManager.getLogger(TicketStatusService.class);
    
    @PostConstruct
    public void init() {

        for (TicketStatusEnum status : TicketStatusEnum.values()) {
            if (!ticketStatusRepository.existsById(status.getId())) {
                TicketStatus ticketStatus = new TicketStatus();
                ticketStatus.setId(status.getId());
                ticketStatus.setStatus(status.getDescription());
                ticketStatusRepository.save(ticketStatus);
            }
        }
    }

    public ServiceResponse<List<TicketStatusDTO>> findBy() {
        ServiceResponse<List<TicketStatusDTO>> resp = new ServiceResponse<>();
        try {
            List<TicketStatus> list = ticketStatusRepository.findAll();
            resp.setObj(TicketStatusConverter.convertToDTO(list));
            resp.setOK();
        }
        catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            resp.setMessage(ex.getMessage());
        }
        return resp;
    }
}
