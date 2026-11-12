package com.techflow.ws.support.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techflow.ws.sys.dto.SysUserDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDTO {
    private Integer id;
    private Integer statusId;
    private TicketStatusDTO status;
    private Integer categoryId;
    private TicketCategoryDTO category;
    private Integer priorityId;
    private TicketPriorityDTO priority;
    private String subject;
    private String description;
    private Integer userId;
    private SysUserDTO user;
    private Integer supportUserId;
    private SysUserDTO supportUser;
    private Instant createDate;
    private Instant updateDate;

    private List<TicketDetailDTO> details = new ArrayList<>();

    public TicketDTO() {}

    public TicketDTO(Integer id, TicketStatusDTO status, TicketCategoryDTO category, TicketPriorityDTO priority, String subject, String description, SysUserDTO user) {
        this.id = id;
        this.status = status;
        this.category = category;
        this.priority = priority;
        this.subject = subject;
        this.description = description;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public TicketStatusDTO getStatus() {
        return status;
    }
    public void setStatus(TicketStatusDTO status) {
        this.status = status;
    }
    public TicketCategoryDTO getCategory() {
        return category;
    }
    public void setCategory(TicketCategoryDTO category) {
        this.category = category;
    }
    public TicketPriorityDTO getPriority() {
        return priority;
    }
    public void setPriority(TicketPriorityDTO priority) {
        this.priority = priority;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
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

    public List<TicketDetailDTO> getDetails() {
        return details;
    }
    public void setDetails(List<TicketDetailDTO> details) {
        this.details = details;
    }

    public Integer getStatusId() {
        return statusId;
    }
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPriorityId() {
        return priorityId;
    }
    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSupportUserId() {
        return supportUserId;
    }
    public void setSupportUserId(Integer supportUserId) {
        this.supportUserId = supportUserId;
    }
    public SysUserDTO getSupportUser() {
        return supportUser;
    }
    public void setSupportUser(SysUserDTO supportUser) {
        this.supportUser = supportUser;
    }

    public Instant getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }
    public Instant getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }
    
}
