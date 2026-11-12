package com.techflow.ws.support.converter;

import java.util.ArrayList;
import java.util.List;

import com.techflow.ws.support.dto.TicketDetailDTO;
import com.techflow.ws.support.entity.Ticket;
import com.techflow.ws.support.entity.TicketDetail;
import com.techflow.ws.support.entity.TicketStatus;
import com.techflow.ws.sys.converter.SysUserConverter;
import com.techflow.ws.sys.entity.SysUser;

public class TicketDetailConverter {

    public static TicketDetailDTO convertToDTO(TicketDetail entity) {
        if (entity == null) {
            return null;
        }
        TicketDetailDTO dto = new TicketDetailDTO();
        dto.setId(entity.getId());
        dto.setTicketId(entity.getTicket().getId());
        dto.setStatusId(entity.getStatus().getId());
        dto.setStatus(TicketStatusConverter.convertToDTO(entity.getStatus()));        
        dto.setDescription(entity.getDescription());
        dto.setUserId(entity.getUser().getId());
        dto.setUser(SysUserConverter.convertToDTO(entity.getUser(), false, false, false));
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }

    public static TicketDetail convertTo(TicketDetailDTO dto) {
        if (dto == null) {
            return null;
        }
        TicketDetail entity = new TicketDetail();
        entity.setId(dto.getId());
        entity.setTicket(new Ticket(dto.getTicketId()));
        entity.setStatus(new TicketStatus(dto.getStatusId()));        
        entity.setDescription(dto.getDescription());
        entity.setUser(new SysUser(dto.getUserId()));
        entity.setCreateDate(dto.getCreateDate());
        
        return entity;
    }

    public static List<TicketDetailDTO> convertToDTO(List<TicketDetail> entities) {
        if (entities == null) {
            return null;
        }
        List<TicketDetailDTO> dtoList = new ArrayList<>();
        for (TicketDetail entity : entities) {
            dtoList.add(convertToDTO(entity));
        }
        return dtoList;
    }

    public static List<TicketDetail> convertTo(List<TicketDetailDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<TicketDetail> entityList = new ArrayList<>();
        for (TicketDetailDTO dto : dtos) {
            entityList.add(convertTo(dto));
        }
        return entityList;
    }

}
