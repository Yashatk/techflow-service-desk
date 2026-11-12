package com.techflow.ws.support.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techflow.ws.sys.dto.SysUserDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDetailDTO {
    private Integer id;
    private Integer ticketId;
    private Integer statusId;
    private TicketStatusDTO status;
    private String description;
    private Integer userId;
    private SysUserDTO user;

    private Instant createDate;

    public TicketDetailDTO() {}

    public TicketDetailDTO(Integer id, Integer ticketId, TicketStatusDTO status, String description, SysUserDTO user) {
        this.id = id;
        this.ticketId = ticketId;
        this.status = status;
        this.description = description;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getTicketId() {
        return ticketId;
    }
    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
    public TicketStatusDTO getStatus() {
        return status;
    }
    public void setStatus(TicketStatusDTO status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public SysUserDTO getUser() {
        return user;
    }
    public void setUser(SysUserDTO user) {
        this.user = user;
    }
    public Integer getStatusId() {
        return statusId;
    }
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public Instant getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }
}
