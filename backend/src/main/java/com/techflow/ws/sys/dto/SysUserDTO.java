package com.techflow.ws.sys.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserDTO {
    private Integer id;
    private Integer st = 1;
    private String username;
    private String password;
    private String name;
    private String email;
    private String sid;

    private List<SysGroupDTO> groups;

    public SysUserDTO() {}

    public SysUserDTO(Integer id, String username, String password, String name, String email, String sid) {
        this.id = id;
        this.st = 1;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.sid = sid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public List<SysGroupDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<SysGroupDTO> groups) {
        this.groups = groups;
    }
    public Integer getSt() {
        return st;
    }
    public void setSt(Integer st) {
        this.st = st;
    }
}
