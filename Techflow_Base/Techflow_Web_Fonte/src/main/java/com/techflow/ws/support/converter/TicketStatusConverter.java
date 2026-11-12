package com.techflow.ws.support.converter;

import java.util.ArrayList;
import java.util.List;

import com.techflow.ws.support.dto.TicketStatusDTO;
import com.techflow.ws.support.entity.TicketStatus;

public class TicketStatusConverter {
    public static TicketStatusDTO convertToDTO(TicketStatus entity) {
        if (entity == null) {
            return null;
        }
        return new TicketStatusDTO(entity.getId(), entity.getStatus());
    }

    public static TicketStatus convertTo(TicketStatusDTO dto) {
        if (dto == null) {
            return null;
        }
        TicketStatus entity = new TicketStatus(dto.getId(), dto.getStatus());
        return entity;
    }

    public static List<TicketStatusDTO> convertToDTO(List<TicketStatus> entities) {
        if (entities == null) {
            return null;
        }
        List<TicketStatusDTO> dtoList = new ArrayList<>();
        for (TicketStatus entity : entities) {
            dtoList.add(convertToDTO(entity));
        }
        return dtoList;
    }

    public static List<TicketStatus> convertTo(List<TicketStatusDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<TicketStatus> entityList = new ArrayList<>();
        for (TicketStatusDTO dto : dtos) {
            entityList.add(convertTo(dto));
        }
        return entityList;
    }
}
