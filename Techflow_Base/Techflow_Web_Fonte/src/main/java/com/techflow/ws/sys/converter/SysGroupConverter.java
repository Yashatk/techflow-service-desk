package com.techflow.ws.sys.converter;

import java.util.ArrayList;
import java.util.List;

import com.techflow.ws.sys.dto.SysGroupDTO;
import com.techflow.ws.sys.entity.SysGroup;

public class SysGroupConverter {
    public static SysGroupDTO convertToDTO(SysGroup group, Boolean accesses, Boolean users) {
        if (group == null) {
            return null;
        }
        SysGroupDTO dto = new SysGroupDTO();
        dto.setId(group.getId());
        dto.setGroupName(group.getGroupName());
        dto.setDescription(group.getDescription());
        if(accesses)
            dto.setAccesses(SysAccessConverter.convertToDTO(group.getAccesses()));
        if(users)
            dto.setUsers(SysUserConverter.convertToDTO(group.getUsers(), false, false, false));
        return dto;
    }

    public static SysGroup convertTo(SysGroupDTO dto) {
        if (dto == null) {
            return null;
        }
        SysGroup group = new SysGroup();
        group.setId(dto.getId());
        group.setGroupName(dto.getGroupName());
        group.setDescription(dto.getDescription());
        return group;
    }

    public static List<SysGroupDTO> convertToDTO(List<SysGroup> groups, Boolean accesses, Boolean users) {
        if (groups == null) {
            return null;
        }
        List<SysGroupDTO> dtoList = new ArrayList<>();
        for (SysGroup group : groups) {
            dtoList.add(convertToDTO(group, accesses, users));
        }
        return dtoList;
    }

    public static List<SysGroup> convertTo(List<SysGroupDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        List<SysGroup> groups = new ArrayList<>();
        for (SysGroupDTO dto : dtoList) {
            groups.add(convertTo(dto));
        }
        return groups;
    }
}
