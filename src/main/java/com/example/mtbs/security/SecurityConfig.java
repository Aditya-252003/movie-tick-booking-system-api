package com.example.mtbs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //Disabling CSRF protection
        httpSecurity.csrf(csrf-> csrf.disable());

        //Specify public and private routes
        httpSecurity.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST
                        , "/register")
                .permitAll()
                .anyRequest().authenticated());

        //types of authentication to be done
        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
