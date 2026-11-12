package com.techflow.ws.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techflow.ws.sys.entity.SysGroupUser;
import com.techflow.ws.sys.entity.SysGroupUserId;

public interface SysGroupUserRepository extends JpaRepository<SysGroupUser, SysGroupUserId> {

}
