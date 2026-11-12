package com.techflow.ws.sys.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class CommonEntity {
    /*
     * Habilitado (1) / Desabilitado (0)
     */
    @Column(name="status", length=1, nullable=false)
    private Integer st = 1;

    public Integer getSt() {
        return st;
    }
    public void setSt(Integer st) {
        this.st = st;
    }
    
    
}
