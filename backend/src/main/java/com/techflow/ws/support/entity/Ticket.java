package com.techflow.ws.support.entity;

import java.util.ArrayList;
import java.util.List;

import com.techflow.ws.sys.entity.CommonTimestampEntity;
import com.techflow.ws.sys.entity.SysUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tflow_chamado")
public class Ticket extends CommonTimestampEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idchamado", length=9, nullable=false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="idstatus", nullable=false)
    private TicketStatus status;

    @ManyToOne
    @JoinColumn(name="idcategoria", nullable=false, foreignKey = @ForeignKey(name="fktflow_chamado_categoria"))
    private TicketCategory category;

    @ManyToOne
    @JoinColumn (name="idprioridade", nullable=true, foreignKey = @ForeignKey(name="fktflow_chamado_prioridade"))
    private TicketPriority priority;

    @Column(name="assunto", length=100, nullable=false)
    private String subject;

    @Column(name="descricao", columnDefinition = "TEXT", nullable=true)
    private String description;

    @ManyToOne
    @JoinColumn(name="idusuario", nullable=false, foreignKey = @ForeignKey(name="fktflow_chamado_usuario"))
    private SysUser user;

    @ManyToOne
    @JoinColumn(name="idusuariosuporte", nullable=true, foreignKey = @ForeignKey(name="fktflow_chamado_usuario_suporte"))
    private SysUser supportUser;

    @OneToMany(mappedBy="ticket", orphanRemoval=true, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<TicketDetail> details = new ArrayList<>();

    public Ticket() {
    }
    public Ticket(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public TicketCategory getCategory() {
        return category;
    }
    public String getSubject() {
        return subject;
    }
    public String getDescription() {
        return description;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setCategory(TicketCategory category) {
        this.category = category;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public TicketStatus getStatus() {
        return status;
    }
    public void setStatus(TicketStatus status) {
        this.status = status;
    }
    public TicketPriority getPriority() {
        return priority;
    }
    public void setPriority(TicketPriority priority) {
        this.priority = priority;
    }
    public SysUser getUser() {
        return user;
    }
    public void setUser(SysUser user) {
        this.user = user;
    }

    public List<TicketDetail> getDetails() {
        return details;
    }
    public void setDetails(List<TicketDetail> details) {
        this.details = details;
    }
    public SysUser getSupportUser() {
        return supportUser;
    }
    public void setSupportUser(SysUser supportUser) {
        this.supportUser = supportUser;
    }
}
