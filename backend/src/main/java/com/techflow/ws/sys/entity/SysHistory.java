package com.techflow.ws.sys.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tflow_historico")
public class SysHistory extends CommonTimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idhistorico", length=9, nullable=false)
    private Integer id;

    @Column(name="idhistorico_tipo", length=30, nullable=true)
    private String historyTypeId;

    @Column(name="descricao", columnDefinition = "TEXT", nullable=true)
    private String description;

    public SysHistory() {
    }
    public SysHistory(Integer id) {
        this.id = id;
    }

    public SysHistory(String historyTypeId, String description) {
        this.historyTypeId = historyTypeId;
        this.description = description;
    }
    
    public Integer getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getHistoryTypeId() {
        return historyTypeId;
    }
    public void setHistoryTypeId(String historyTypeId) {
        this.historyTypeId = historyTypeId;
    }

}
