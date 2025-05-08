package com.example.mtbs.service.impl;

import com.example.mtbs.dto.AuthResponse;
import com.example.mtbs.dto.LoginRequest;
import com.example.mtbs.enitity.UserDetails;
import com.example.mtbs.mapper.AuthMapper;
import com.example.mtbs.repository.UserRepository;
import com.example.mtbs.security.SecurityConfig;
import com.example.mtbs.security.jwt.JwtService;
import com.example.mtbs.security.jwt.TokenPayLoad;
import com.example.mtbs.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final SecurityConfig securityConfig;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthMapper authMapper;
    private final UserRepository userRepository;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());

        Authentication authentication = authenticationManager.authenticate(token);

        if (! authentication.isAuthenticated())
            throw new UsernameNotFoundException("Invalid Login Details");

        UserDetails userDetails = userRepository.findByEmail(authentication.getName());

        TokenPayLoad access = tokenGenerator(userDetails, 5 );
        TokenPayLoad refresh = tokenGenerator(userDetails, 24*60 );

        String accessToken = jwtService.createJwtToken(access);
        String refreshToken = jwtService.createJwtToken(refresh);

        return authMapper.authResponseMapper(userDetails,access ,refresh ,accessToken , refreshToken);
    }

    private TokenPayLoad tokenGenerator(UserDetails userDetails, int minutesForExpiration){
        Map<String, Object> claims = new HashMap<>();

        String role = userDetails.getUserRole().toString();
        claims.put("role", role);

        return new TokenPayLoad(
                claims,
                userDetails.getEmail(),
                Instant.now(),
                Instant.now().plusSeconds(minutesForExpiration*60)
        );
    }
}

