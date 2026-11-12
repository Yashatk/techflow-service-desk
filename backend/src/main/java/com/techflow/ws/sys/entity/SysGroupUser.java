package com.techflow.ws.sys.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/*
 * Usuários por grupos de acesso
 */
@Entity
@Table(name="tflow_grupo_usuario")
public class SysGroupUser {
    
    @EmbeddedId
    private SysGroupUserId id;

    @ManyToOne
    @MapsId("group")
    @JoinColumn(name="idgrupo")
    private SysGroup group;

    @ManyToOne
    @MapsId("user")
    @JoinColumn(name = "idusuario")
    private SysUser user;

    public SysGroupUser() {
    }
    public SysGroupUser(SysGroupUserId id) {
        this.id = id;
    }
    public SysGroupUser(SysGroup group, SysUser user) {
        this.group = group;
        this.id = new SysGroupUserId(group, user);
    }
    public SysGroupUserId getId() {
        return id;
    }
    public void setId(SysGroupUserId id) {
        this.id = id;
    }

    public SysGroup getGroup() {
        return group;        
    }
    public void setGroup(SysGroup group) {
        this.group = group;
    }

    public SysUser getUser() {
        return user;
    }
    public void setUser(SysUser user) {
        this.user = user;
    }

}
