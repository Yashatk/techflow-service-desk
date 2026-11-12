package com.techflow.ws.sys.converter;

import java.util.ArrayList;
import java.util.List;

import com.techflow.ws.sys.dto.SysUserDTO;
import com.techflow.ws.sys.entity.SysUser;

public class SysUserConverter {

    public static SysUserDTO convertToDTO(SysUser sysUser, Boolean groups, Boolean accesses, Boolean users) {
        if (sysUser == null) {
            return null;
        }
        SysUserDTO dto = new SysUserDTO();
        dto.setId(sysUser.getId());
        dto.setSt(sysUser.getSt());
        dto.setUsername(sysUser.getUsername());
        dto.setName(sysUser.getName());
        dto.setEmail(sysUser.getEmail());

        if(groups)
            dto.setGroups(SysGroupConverter.convertToDTO(sysUser.getGroups(), accesses, users));

        return dto;
    }

    public static SysUser convertTo(SysUserDTO dto) {
        if (dto == null) {
            return null;
        }
        SysUser sysUser = new SysUser();
        sysUser.setId(dto.getId());
        sysUser.setSt(dto.getSt());
        sysUser.setUsername(dto.getUsername());
        sysUser.setPassword(dto.getPassword());
        sysUser.setName(dto.getName());
        sysUser.setEmail(dto.getEmail());
        sysUser.setSid(dto.getSid());
        return sysUser;
    }

    public static List<SysUserDTO> convertToDTO(List<SysUser> sysUsers, Boolean groups, Boolean accesses, Boolean users) {
        if (sysUsers == null) {
            return null;
        }
        List<SysUserDTO> dtoList = new ArrayList<>();
        for (SysUser sysUser : sysUsers) {
            dtoList.add(convertToDTO(sysUser, groups, accesses, users));
        }
        return dtoList;
    }

    public static List<SysUser> convertTo(List<SysUserDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        List<SysUser> sysUsers = new ArrayList<>();
        for (SysUserDTO dto : dtoList) {
            sysUsers.add(convertTo(dto));
        }
        return sysUsers;
    }
}
