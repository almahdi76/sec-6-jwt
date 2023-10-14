package com.jwtsec;

import com.jwtsec.sec.entities.AppRole;
import com.jwtsec.sec.entities.AppUser;
import com.jwtsec.sec.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtsecApplication {

	private AppUser user1;

	public static void main(String[] args) {
		SpringApplication.run(JwtsecApplication.class, args);
	}
	//@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(AccountService accountService){
		return args -> {
			accountService.addNewRole(new AppRole(null,"USER"));
			accountService.addNewRole(new AppRole(null,"ADMIN"));
			accountService.addNewRole(new AppRole(null,"MANAGER"));

			accountService.addNewUser(new AppUser(null,"user1","1234",new ArrayList<>()));
			accountService.addNewUser(new AppUser(null,"user2","1234",new ArrayList<>()));
			accountService.addNewUser(new AppUser(null,"user3","1234",new ArrayList<>()));

			accountService.addRoleToUser("user1","USER");
			accountService.addRoleToUser("user1","ADMIN");
			accountService.addRoleToUser("user2","USER");
			accountService.addRoleToUser("user3","USER");
			accountService.addRoleToUser("user3","MANAGER");
		};
	}

}
