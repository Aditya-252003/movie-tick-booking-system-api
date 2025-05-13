package com.example.mtbs.security;

import com.example.mtbs.enums.TokenType;
import com.example.mtbs.security.filter.AuthFilter;
import com.example.mtbs.security.jwt.JwtService;
import jakarta.security.auth.message.config.AuthConfig;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final JwtService jwtService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Order(2)
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.securityMatcher("/**");

        //Disabling CSRF protection
        httpSecurity.csrf(csrf -> csrf.disable());

        //Specify public and private routes
        httpSecurity.authorizeHttpRequests(auth -> auth.requestMatchers
                        (HttpMethod.POST, "/register", "/login")

                .permitAll()
                .anyRequest()
                .authenticated());

        httpSecurity.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(new AuthFilter(jwtService, TokenType.ACCESS), UsernamePasswordAuthenticationFilter.class);

        //types of authentication to be done
//       httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager AuthenticationManager(AuthenticationConfiguration Configuration) throws Exception {
        return Configuration.getAuthenticationManager();
    }


    @Bean
    @Order(1)
    SecurityFilterChain refreshFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.securityMatcher("/refresh/**");
        httpSecurity.authorizeHttpRequests(auth -> auth
                .anyRequest()
                .authenticated());

        setdefault(new AuthFilter(jwtService, TokenType.REFRESH), httpSecurity);

        return httpSecurity.build();
    }

    private HttpSecurity setdefault(AuthFilter authFilter, HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity;
    }

}
