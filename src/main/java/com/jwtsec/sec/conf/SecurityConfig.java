package com.jwtsec.sec.conf;

import com.jwtsec.sec.entities.AppUser;
import com.jwtsec.sec.service.AccountService;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Value("${jwt.secret}")
    private String secretKey;
    @Bean
      SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
          return  httpSecurity.csrf(c->c.disable())
                  .authorizeHttpRequests(au->au.anyRequest().authenticated())
                 // .httpBasic(Customizer.withDefaults())
                 // .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt) ou mieux
                  .oauth2ResourceServer(ou->ou.jwt(Customizer.withDefaults()))
                 .build();
    }
@Bean
    JwtEncoder jwtEncoder(){
        //String secretKey="8df43afa5b3244809ea63d242afa1a6a8df43afa5b3244809ea63d242afa1a6a";
        return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey.getBytes()));
    }
    JwtDecoder jwtDecoder(){
        //String secretKey="8df43afa5b3244809ea63d242afa1a6a8df43afa5b3244809ea63d242afa1a6a";
        SecretKeySpec secretKeySpec=new SecretKeySpec(secretKey.getBytes(),"RSA");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS256).build();
    }




    //@Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
         httpSecurity.csrf(c->c.disable());
         //httpSecurity.sessionManagement(se->se.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
         httpSecurity.authorizeHttpRequests(au->au.requestMatchers("/login/**").permitAll());
            httpSecurity.authorizeHttpRequests(au->au.anyRequest().authenticated());
            httpSecurity.formLogin(Customizer.withDefaults());
           // httpSecurity.userDetailsService(userDetailServiceImp);

        /*
             return  httpSecurity.csrf(c->c.disable())
                  .authorizeHttpRequests(au->au.anyRequest().authenticated())
                  .httpBasic(Customizer.withDefaults())
                 .build();
         */
                return httpSecurity.build();
    }
}
