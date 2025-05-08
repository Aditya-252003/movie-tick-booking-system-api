package com.example.mtbs.mapper;

import com.example.mtbs.dto.AuthResponse;
import com.example.mtbs.enitity.User;
import com.example.mtbs.enitity.UserDetails;
import com.example.mtbs.security.jwt.TokenPayLoad;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public AuthResponse authResponseMapper(UserDetails details,
                                           TokenPayLoad accessToken,
                                           TokenPayLoad refreshToken
                                          , String access,
                                           String refresh) {
        return AuthResponse.builder()
                .userId(details.getUserId())
                .username(details.getUsername())
                .email(details.getEmail())
                .role(details.getUserRole())
                .accesExpiration(accessToken.expiration().toEpochMilli())
                .refreshExpiration(refreshToken.expiration().toEpochMilli())
                .accessToken(accessToken)
                .refreshtoken(refreshToken)
                .build();
    }

}
