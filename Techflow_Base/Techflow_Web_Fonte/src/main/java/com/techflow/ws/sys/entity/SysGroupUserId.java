package com.techflow.ws.sys.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class SysGroupUserId {

    @ManyToOne
    @JoinColumn(name="idgrupo", nullable=false, foreignKey = @ForeignKey(name = "fktflow_grupo_usuario_grupo"))
    private SysGroup group;

    @ManyToOne
    @JoinColumn(name="idusuario", nullable=false, foreignKey = @ForeignKey(name = "fktflow_grupo_usuario_usuario"))
    private SysUser user;

    public SysGroupUserId() {
    }

    public SysGroupUserId(SysGroup group, SysUser user) {
        this.group = group;
        this.user = user;
    }
    
    public SysGroup getGroup() {
        return group;
    }
    public SysUser getUser() {
        return user;
    }
    public void setGroup(SysGroup group) {
        this.group = group;
    }
    public void setUser(SysUser user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysGroupUserId that = (SysGroupUserId) o;
        return java.util.Objects.equals(group, that.group) &&
               java.util.Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(group, user);
    }
}
