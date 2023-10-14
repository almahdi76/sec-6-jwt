package com.jwtsec.sec.service;

import com.jwtsec.sec.entities.AppRole;
import com.jwtsec.sec.entities.AppUser;

import java.util.List;

public interface IAccountService {

    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole role);
    void addRoleToUser(String username,String role);

    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();
}
