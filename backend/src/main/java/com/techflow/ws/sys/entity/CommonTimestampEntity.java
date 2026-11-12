package com.techflow.ws.sys.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class CommonTimestampEntity extends CommonEntity {

    /*
     * Data de Criação do Registro - Automático
     */
    @CreationTimestamp
    @Column(name="datacriacao")
    private Instant createDate;

    /*
     * Data de Atualização do Registro - Automático
     */
    @UpdateTimestamp
    @Column(name="dataatualizacao")
    private Instant updateDate;

    public Instant getCreateDate() {
        return createDate;
    }
    public Instant getUpdateDate() {
        return updateDate;
    }
    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }
}
