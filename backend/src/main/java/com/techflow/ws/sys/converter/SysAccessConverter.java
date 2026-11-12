package com.techflow.ws.sys.converter;

import java.util.ArrayList;
import java.util.List;

import com.techflow.ws.sys.dto.SysAccessDTO;
import com.techflow.ws.sys.entity.SysAccess;

public class SysAccessConverter {

    public static SysAccessDTO convertToDTO(SysAccess sysAccess) {
        if (sysAccess == null) {
            return null;
        }
        SysAccessDTO dto = new SysAccessDTO();
        dto.setId(sysAccess.getId());
        dto.setDescription(sysAccess.getDescription());
        return dto;
    }
    public static SysAccess convertToEntity(SysAccessDTO dto) {
        if (dto == null) {
            return null;
        }
        SysAccess sysAccess = new SysAccess();
        sysAccess.setId(dto.getId());
        sysAccess.setDescription(dto.getDescription());
        return sysAccess;
    }
    
    public static List<SysAccessDTO> convertToDTO(List<SysAccess> sysAccessList) {
        if (sysAccessList == null) {
            return null;
        }
        List<SysAccessDTO> dtoList = new ArrayList<>();
        for (SysAccess sysAccess : sysAccessList) {
            dtoList.add(convertToDTO(sysAccess));
        }
        return dtoList;
    }

    public static List<SysAccess> convertTo(List<SysAccessDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        List<SysAccess> sysAccessList = new ArrayList<>();
        for (SysAccessDTO dto : dtoList) {
            sysAccessList.add(convertToEntity(dto));
        }
        return sysAccessList;
    }
}
