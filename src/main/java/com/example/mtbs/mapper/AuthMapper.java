package com.example.mtbs.mapper;

import com.example.mtbs.dto.AuthResponse;
import com.example.mtbs.enitity.User;
import com.example.mtbs.enitity.UserDetails;
import com.example.mtbs.security.jwt.TokenPayLoad;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public AuthResponse authResponseMapper(UserDetails details,
                                           TokenPayLoad access,
                                           TokenPayLoad refresh
                                          , String accessToken,
                                           String refreshToken) {
        return AuthResponse.builder()
                .userId(details.getUserId())
                .username(details.getUsername())
                .email(details.getEmail())
                .role(String.valueOf(details.getUserRole()))
                .accesExpiration(access.expiration().toEpochMilli())
                .refreshExpiration(refresh.expiration().toEpochMilli())
                .accessToken(accessToken)
                .refreshtoken(refreshToken)
                .build();
    }

}
