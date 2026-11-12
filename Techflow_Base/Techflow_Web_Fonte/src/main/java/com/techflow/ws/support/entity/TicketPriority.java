package com.techflow.ws.support.entity;

import com.techflow.ws.sys.entity.CommonEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tflow_chamado_prioridade")
public class TicketPriority extends CommonEntity {

    @Id
    @Column(name="idprioridade", length=2, nullable=false)
    private Integer id;

    @Column(name="prioridade", length=50, nullable=false)
    private String priority;

    public TicketPriority() {
    }
    public TicketPriority(Integer id) {
        this.id = id;
    }
    public TicketPriority(Integer id, String priority) {
        this.id = id;
        this.priority = priority;
    }
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
}
