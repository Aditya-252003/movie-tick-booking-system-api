package com.example.mtbs.service;


import com.example.mtbs.dto.AuthResponse;
import com.example.mtbs.dto.LoginRequest;
import com.example.mtbs.enitity.UserDetails;
import com.example.mtbs.security.jwt.AuthenticatedTokenDetails;
import org.springframework.security.core.Authentication;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse refresh(AuthenticatedTokenDetails details);
}
