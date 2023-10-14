package com.jwtsec.sec.repo;

import com.jwtsec.sec.entities.AppRole;
import com.jwtsec.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {

    AppRole findByRoleName(String role);
}
