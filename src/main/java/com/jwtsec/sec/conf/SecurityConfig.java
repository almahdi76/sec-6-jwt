package com.jwtsec.sec.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
         httpSecurity.csrf(c->c.disable());
         httpSecurity.sessionManagement(se->se.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
         httpSecurity.authorizeHttpRequests(au->au.requestMatchers("/users").permitAll());




                return httpSecurity.build();
    }
}
