package com.techflow.ws.support.converter;

import java.util.ArrayList;
import java.util.List;

import com.techflow.ws.support.dto.TicketDTO;
import com.techflow.ws.support.entity.Ticket;
import com.techflow.ws.support.entity.TicketCategory;
import com.techflow.ws.support.entity.TicketPriority;
import com.techflow.ws.support.entity.TicketPriorityEnum;
import com.techflow.ws.support.entity.TicketStatus;
import com.techflow.ws.support.entity.TicketStatusEnum;
import com.techflow.ws.sys.converter.SysUserConverter;
import com.techflow.ws.sys.entity.SysUser;

public class TicketConverter {

    public static TicketDTO convertToDTO(Ticket entity) {
        if (entity == null) {
            return null;
        }
        TicketDTO dto = new TicketDTO();
        dto.setId(entity.getId());
        dto.setSubject(entity.getSubject());
        dto.setDescription(entity.getDescription());
        dto.setStatusId(entity.getStatus() != null && entity.getStatus().getId() != null ? entity.getStatus().getId() : null);
        dto.setStatus(TicketStatusConverter.convertToDTO(entity.getStatus()));
        dto.setCategoryId(entity.getCategory() != null && entity.getCategory().getId() != null ? entity.getCategory().getId() : null);
        dto.setCategory(TicketCategoryConverter.convertToDTO(entity.getCategory()));
        dto.setPriorityId(entity.getPriority() != null && entity.getPriority().getId() != null ? entity.getPriority().getId() : null);
        dto.setPriority(TicketPriorityConverter.convertToDTO(entity.getPriority()));
        dto.setUserId(entity.getUser() != null && entity.getUser().getId() != null ? entity.getUser().getId() : null);
        dto.setUser(SysUserConverter.convertToDTO(entity.getUser(), false, false, false));
        if(entity.getSupportUser() != null) {
            dto.setSupportUserId(entity.getSupportUser().getId());
            dto.setSupportUser(SysUserConverter.convertToDTO(entity.getSupportUser(), false, false, false));
        }
        // Detalhes do Ticket
        dto.setDetails(TicketDetailConverter.convertToDTO(entity.getDetails()));
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateDate(entity.getUpdateDate());
        return dto;
    }

    public static Ticket convertTo(TicketDTO dto) {
        if (dto == null) {
            return null;
        }
        Ticket entity = new Ticket();
        entity.setId(dto.getId());
        entity.setStatus(new TicketStatus(dto.getStatusId() != null ? dto.getStatusId() : TicketStatusEnum.ABERTO.getId()));
        if(dto.getCategoryId() != null && dto.getCategoryId() > 0) {
            entity.setCategory(new TicketCategory(dto.getCategoryId()));
        }
        entity.setPriority(new TicketPriority(dto.getPriorityId() != null ? dto.getPriorityId() : TicketPriorityEnum.BAIXA.getId()));
        entity.setSubject(dto.getSubject());
        entity.setDescription(dto.getDescription());
        if(dto.getUserId() != null) {
            entity.setUser(new SysUser(dto.getUserId()));
        }
        if(dto.getSupportUserId() != null) {
            entity.setSupportUser(new SysUser(dto.getSupportUserId()));
        }
        return entity;
    }

    public static List<TicketDTO> convertToDTO(List<Ticket> entities) {
        if (entities == null) {
            return null;
        }
        List<TicketDTO> dtoList = new ArrayList<>();
        for (Ticket entity : entities) {
            dtoList.add(convertToDTO(entity));
        }
        return dtoList;
    }

    public static List<Ticket> convertTo(List<TicketDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<Ticket> entityList = new ArrayList<>();
        for (TicketDTO dto : dtos) {
            entityList.add(convertTo(dto));
        }
        return entityList;
    }
}
