package com.techflow.ws.sys.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysHistoryDTO {
    private Integer id;
    private String historyTypeId;
    private String description;
    private Instant createDate;

    public SysHistoryDTO() {}

    public SysHistoryDTO(Integer id, String historyTypeId, String description) {
        this.id = id;
        this.historyTypeId = historyTypeId;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHistoryTypeId() {
        return historyTypeId;
    }

    public void setHistoryTypeId(String historyTypeId) {
        this.historyTypeId = historyTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

        public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }
}