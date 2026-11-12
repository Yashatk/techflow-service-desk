package com.techflow.ws.sys.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/*
 * Acessos por grupos de acesso
 */
@Entity
@Table(name="tflow_grupo_acesso")
public class SysGroupAccess {

    @EmbeddedId
    private SysGroupAccessId id;

    @ManyToOne
    @MapsId("group")
    @JoinColumn(name="idgrupo")
    private SysGroup group;

    public SysGroupAccess() {
    }
    public SysGroupAccess(SysGroupAccessId id) {
        this.id = id;
    }
    public SysGroupAccess(SysGroup group, SysAccess access) {
        this.id = new SysGroupAccessId(group, access);
        this.group = group;
    }
    public SysGroupAccessId getId() {
        return id;
    }
    public void setId(SysGroupAccessId id) {
        this.id = id;
    }
   
    public SysGroup getGroup() {
        return group;
    }
    public void setGroup(SysGroup group) {
        this.group = group;
    }
}
