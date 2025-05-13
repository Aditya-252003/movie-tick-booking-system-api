package com.example.mtbs.service.impl;

import com.example.mtbs.dto.AuthResponse;
import com.example.mtbs.dto.LoginRequest;
import com.example.mtbs.enitity.UserDetails;
import com.example.mtbs.enums.TokenType;
import com.example.mtbs.mapper.AuthMapper;
import com.example.mtbs.repository.UserRepository;
import com.example.mtbs.security.SecurityConfig;
import com.example.mtbs.security.jwt.AuthenticatedTokenDetails;
import com.example.mtbs.security.jwt.JwtService;
import com.example.mtbs.security.jwt.TokenPayLoad;
import com.example.mtbs.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

        TokenPayLoad access = tokenGenerator(userDetails, 5 ,TokenType.ACCESS);
        TokenPayLoad refresh = tokenGenerator(userDetails, 24*60 , TokenType.REFRESH);

        String accessToken = jwtService.createJwtToken(access);
        String refreshToken = jwtService.createJwtToken(refresh);

        return authMapper.authResponseMapper(userDetails,access ,refresh ,accessToken , refreshToken);
    }

    @Override
    public AuthResponse refresh(AuthenticatedTokenDetails details) {

        UserDetails userDetails = userRepository.findByEmail(details.email());

        TokenPayLoad generator = tokenGenerator(userDetails,5,TokenType.ACCESS);
        String access = jwtService.createJwtToken(generator);

        return new AuthResponse(userDetails.getUserId(),
                userDetails.getUsername(),
                details.email(),
                details.role(),
                generator.expiration().toEpochMilli(),
                details.tokenExpiration().toEpochMilli(),
                access,
                details.currentToken());
    }

    private TokenPayLoad tokenGenerator(UserDetails userDetails, int minutesForExpiration, TokenType tokenType ){
        Map<String, Object> claims = new HashMap<>();

        String role = userDetails.getUserRole().toString();
        claims.put("role", role);

        log.info("Fetched role");

        return new TokenPayLoad(
                claims,
                userDetails.getEmail(),
                Instant.now(),
                Instant.now().plusSeconds(minutesForExpiration*60),
                tokenType

        );
    }
}

