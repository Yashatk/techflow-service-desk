package com.techflow.ws.support.entity;

import com.techflow.ws.sys.entity.CommonTimestampEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tflow_chamado_categoria")
public class TicketCategory extends CommonTimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcategoria", length=4, nullable=false)
    private Integer id;

    @Column(name="categoria", length=100, nullable=false)
    private String category;

    public TicketCategory() {
    }
    public TicketCategory(Integer id) {
        this.id = id;
    }
    public TicketCategory(String category) {
            this.category = category;
    }
    public TicketCategory(Integer id, String category) {
        this.id = id;
        this.category = category;
    }
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }    
}
