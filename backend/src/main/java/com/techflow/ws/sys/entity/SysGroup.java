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
 * Grupos de Usuários do Sistema
 */
@Entity
@Table(name="tflow_grupo")
public class SysGroup extends CommonTimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idgrupo", length=4, nullable=false)
    private Integer id;

    @Column(name="grupo", length=100, nullable=false, unique=true)
    private String groupName;

    @Column(name="descricao", length=255, nullable=true)
    private String description;

    @OneToMany(mappedBy="group", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<SysGroupAccess> groupAccesses = new ArrayList<>();

    @OneToMany(mappedBy="group", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<SysGroupUser> groupUsers = new ArrayList<>();

    public SysGroup() {
    }
    
    public SysGroup(Integer id) {
        this.id = id;
    }   

    public SysGroup(String groupName, String description) {
        this.groupName = groupName;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    public List<SysAccess> getAccesses() {
        return groupAccesses.stream()
                .map(groupAccess -> groupAccess.getId().getAccess())
                .collect(Collectors.toList());
    }

    public void addAccess(SysAccess access) {
        SysGroupAccess newGroupAccess = new SysGroupAccess(this, access);
        groupAccesses.add(newGroupAccess);
    }

    public void removeAccess(SysAccess access) {
        groupAccesses.removeIf(groupAccess ->
            groupAccess.getId().getAccess().equals(access));
    }

    @Transient
    public List<SysUser> getUsers() {
        return groupUsers.stream()
                .map(groupUser -> groupUser.getId().getUser())
                .collect(Collectors.toList());
    }

    public void addUser(SysUser user) {
        SysGroupUser newGroupUser = new SysGroupUser(this, user);
        groupUsers.add(newGroupUser);
    }

    public void removeUser(SysUser user) {
        groupUsers.removeIf(groupUser ->
            groupUser.getId().getUser().equals(user));
    }
}
