package com.techflow.ws.support.converter;

import java.util.ArrayList;
import java.util.List;

import com.techflow.ws.support.dto.TicketPriorityDTO;
import com.techflow.ws.support.entity.TicketPriority;

public class TicketPriorityConverter {

    public static TicketPriorityDTO convertToDTO(TicketPriority entity) {
        if (entity == null) {
            return null;
        }
        return new TicketPriorityDTO(entity.getId(), entity.getPriority());
    }

    public static TicketPriority convertTo(TicketPriorityDTO dto) {
        if (dto == null) {
            return null;
        }
        TicketPriority entity = new TicketPriority(dto.getId(), dto.getPriority());
        return entity;
    }

    public static List<TicketPriorityDTO> convertToDTO(List<TicketPriority> entities) {
        if (entities == null) {
            return null;
        }
        List<TicketPriorityDTO> dtoList = new ArrayList<>();
        for (TicketPriority entity : entities) {
            dtoList.add(convertToDTO(entity));
        }
        return dtoList;
    }

    public static List<TicketPriority> convertTo(List<TicketPriorityDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<TicketPriority> entityList = new ArrayList<>();
        for (TicketPriorityDTO dto : dtos) {
            entityList.add(convertTo(dto));
        }
        return entityList;
    }
}
