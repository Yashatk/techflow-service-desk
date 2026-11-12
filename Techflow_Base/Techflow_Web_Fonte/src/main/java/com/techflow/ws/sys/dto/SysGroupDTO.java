package com.techflow.ws.sys.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysGroupDTO {
    private Integer id;
    private String groupName;
    private String description;

    private List<SysAccessDTO> accesses;
    private List<SysUserDTO> users;

    public SysGroupDTO() {}

    public SysGroupDTO(Integer id, String groupName, String description) {
        this.id = id;
        this.groupName = groupName;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SysAccessDTO> getAccesses() {
        return accesses;
    }
    public void setAccesses(List<SysAccessDTO> accesses) {
        this.accesses = accesses;
    }
    public List<SysUserDTO> getUsers() {
        return users;
    }
    public void setUsers(List<SysUserDTO> users) {
        this.users = users;
    }
}

