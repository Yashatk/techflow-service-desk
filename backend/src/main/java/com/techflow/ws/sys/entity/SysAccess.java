package com.techflow.ws.sys.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * Acessos do Sistema
 */
@Entity
@Table(name="tflow_acesso")
public class SysAccess extends CommonEntity {

    @Id
    @Column(name="idacesso", length=30, nullable=false)
    private String id;

    @Column(name="descricao", length=255, nullable=true)
    private String description;

    public SysAccess() {
    }
    public SysAccess(String id) {
        this.id = id;
    }
    public SysAccess(String id, String description) {
        this.id = id;
        this.description = description;
    }
    public String getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setDescription(String description) {
        this.description = description;
    }


}
