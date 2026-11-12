package com.techflow.ws.sys.converter;

import java.util.ArrayList;
import java.util.List;

import com.techflow.ws.sys.dto.SysHistoryDTO;
import com.techflow.ws.sys.entity.SysHistory;

public class SysHistoryConverter {

    public static SysHistoryDTO convertToDTO(SysHistory entity) {
        if (entity == null) {
            return null;
        }
        SysHistoryDTO dto = new SysHistoryDTO();
        dto.setId(entity.getId());
        dto.setHistoryTypeId(entity.getHistoryTypeId());
        dto.setDescription(entity.getDescription());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }

    public static SysHistory convertTo(SysHistoryDTO dto) {
        if (dto == null) {
            return null;
        }
        SysHistory entity = new SysHistory();
        entity.setId(dto.getId());
        entity.setHistoryTypeId(dto.getHistoryTypeId());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public static List<SysHistoryDTO> convertToDTO(List<SysHistory> entities) {
        if (entities == null) {
            return null;
        }
        List<SysHistoryDTO> dtoList = new ArrayList<>();
        for (SysHistory entity : entities) {
            dtoList.add(convertToDTO(entity));
        }
        return dtoList;
    }

    public static List<SysHistory> convertTo(List<SysHistoryDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        List<SysHistory> entities = new ArrayList<>();
        for (SysHistoryDTO dto : dtoList) {
            entities.add(convertTo(dto));
        }
        return entities;
    }
}
