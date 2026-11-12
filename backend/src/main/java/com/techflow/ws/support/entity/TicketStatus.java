package com.techflow.ws.support.entity;

import com.techflow.ws.sys.entity.CommonEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tflow_chamado_status")
public class TicketStatus extends CommonEntity {

    @Id
    @Column(name="idstatus", length=4, nullable=false)
    private Integer id;

    @Column(name="status_chamado", length=50, nullable=false)
    private String status;

    public TicketStatus() {
    }
    public TicketStatus(Integer id) {
        this.id = id;
    }
    public TicketStatus(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
