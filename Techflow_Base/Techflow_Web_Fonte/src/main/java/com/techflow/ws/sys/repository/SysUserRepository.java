package com.techflow.ws.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techflow.ws.sys.entity.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Integer> {

    SysUser findByUsername(String username);

    boolean existsByUsername(String username);

    public boolean existsByUsernameIgnoreCase(String username);

    public SysUser findByUsernameAndSt(String toLowerCase, int i);

}
