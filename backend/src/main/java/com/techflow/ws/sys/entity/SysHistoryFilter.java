package com.techflow.ws.sys.entity;

import java.time.Instant;

public class SysHistoryFilter {
    private String historyTypeId;
    private String key;
    private Instant startDate;
    private Instant endDate;
    private Integer page;
    private Integer pageSize;

    public SysHistoryFilter() {}
    
    public String getHistoryTypeId() {
        return historyTypeId;
    }
    public void setHistoryTypeId(String historyTypeId) {
        this.historyTypeId = historyTypeId;
    }
    public Instant getStartDate() {
        return startDate;
    }
    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }
    public Instant getEndDate() {
        return endDate;
    }
    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
}
