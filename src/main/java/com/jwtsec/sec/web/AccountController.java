package com.jwtsec.sec.web;

import com.jwtsec.sec.entities.AppRole;
import com.jwtsec.sec.entities.AppUser;
import com.jwtsec.sec.service.AccountService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAlls(){
        return ResponseEntity.ok(accountService.listUsers());
    }
        @GetMapping("/users/{username}")
    public AppUser getUser(@PathVariable String username){
        return accountService.loadUserByUsername(username);
    }

    @PostMapping("/users")
    public AppUser saveUser(@RequestBody AppUser appUser){
        return accountService.addNewUser(appUser);
    }

    @PostMapping("/role")
    public AppRole saveRole(@RequestBody AppRole appRole){
        return accountService.addNewRole(appRole);
    }
    @PostMapping("/role-user")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        accountService.addRoleToUser(roleUserForm.getUsername(),roleUserForm.getRoleName());
    }

}
@Getter @Setter
class RoleUserForm{
    private String username;
    private String roleName;
}
