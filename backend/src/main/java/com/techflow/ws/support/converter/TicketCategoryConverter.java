package com.techflow.ws.support.converter;

import java.util.ArrayList;
import java.util.List;

import com.techflow.ws.support.dto.TicketCategoryDTO;
import com.techflow.ws.support.entity.TicketCategory;

public class TicketCategoryConverter {
    public static TicketCategoryDTO convertToDTO(TicketCategory entity) {
        if (entity == null) {
            return null;
        }
        return new TicketCategoryDTO(entity.getId(), entity.getCategory());
    }

    public static TicketCategory convertTo(TicketCategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        return new TicketCategory(dto.getId(), dto.getCategory());
    }

    public static List<TicketCategoryDTO> convertToDTO(List<TicketCategory> entities) {
        if (entities == null) {
            return null;
        }
        List<TicketCategoryDTO> dtoList = new ArrayList<>();
        for (TicketCategory entity : entities) {
            dtoList.add(convertToDTO(entity));
        }
        return dtoList;
    }

    public static List<TicketCategory> convertTo(List<TicketCategoryDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        List<TicketCategory> entityList = new ArrayList<>();
        for (TicketCategoryDTO dto : dtos) {
            entityList.add(convertTo(dto));
        }
        return entityList;
    }
}
