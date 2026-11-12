package com.techflow.ws.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techflow.ws.sys.entity.SysGroup;

public interface SysGroupRepository extends JpaRepository<SysGroup, Integer> {

    boolean existsByGroupName(String groupName);

    SysGroup findByGroupName(String string);

    public boolean existsByGroupNameIgnoreCase(String groupName);

}
