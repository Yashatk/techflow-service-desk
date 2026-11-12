package com.techflow.ws.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techflow.ws.sys.entity.SysGroupAccess;
import com.techflow.ws.sys.entity.SysGroupAccessId;

public interface SysGroupAccessRepository extends JpaRepository<SysGroupAccess, SysGroupAccessId> {

}
