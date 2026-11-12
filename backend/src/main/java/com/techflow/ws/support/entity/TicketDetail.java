package com.techflow.ws.support.entity;

import com.techflow.ws.sys.entity.CommonTimestampEntity;
import com.techflow.ws.sys.entity.SysUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tflow_chamado_detalhe")
public class TicketDetail extends CommonTimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idchamado_detalhe", length = 9, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="idchamado", nullable=false, foreignKey = @ForeignKey(name="fktflow_chamado_detalhe_chamado"))
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name="idstatus", nullable=false, foreignKey = @ForeignKey(name="fktflow_chamado_detalhe_status"))
    private TicketStatus status;

    @Column(name="descricao", columnDefinition = "TEXT", nullable=true)
    private String description;

    @ManyToOne
    @JoinColumn(name="idusuario", nullable=false, foreignKey = @ForeignKey(name="fktflow_chamado_detalhe_usuario"))
    private SysUser user;

    public TicketDetail() {
    }
    public Integer getId() {
        return id;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public TicketStatus getStatus() {
        return status;
    }
    public String getDescription() {
        return description;
    }
    public SysUser getUser() {
        return user;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    public void setStatus(TicketStatus status) {
        this.status = status;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setUser(SysUser user) {
        this.user = user;
    }


}
