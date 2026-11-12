package com.techflow.ws.sys.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/*
 * Usuários do Sistema
 */
@Entity
@Table(name="tflow_usuario")
public class SysUser extends CommonTimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idusuario", length=9, nullable=false)
    private Integer id;

    @Column(name="usuario", length=50, nullable=false, unique=true)
    private String username;

    /*
     * Senha criptografada
     */
    @Column(name="senha", length=100, nullable=false)
    private String password;

    @Column(name="nome", length=100, nullable=false)
    private String name;

    /*
     * Email do Usuário
     */
    @Column(name="email", length=100, nullable=false)
    private String email;

    /*
     * Session ID para controle de sessão
     */
    @Column(name="sid", length=100, nullable=true)
    private String sid;

    @OneToMany(mappedBy="user", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<SysGroupUser> groupUsers = new ArrayList<>();

    public SysUser() {
    }

    public SysUser(Integer id) {
        this.id = id;
    }

    public SysUser(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Transient
    public List<SysGroup> getGroups() {
        return groupUsers.stream()
                .map(groupUser -> groupUser.getId().getGroup())
                .collect(Collectors.toList());
    }

    public void addGroup(SysGroup group) {
        SysGroupUser newGroupUser = new SysGroupUser(group, this);
        groupUsers.add(newGroupUser);
    }
    
    public void removeGroup(SysGroup group) {
        groupUsers.removeIf(groupUser ->
            groupUser.getId().getGroup().equals(group));
    }


}
