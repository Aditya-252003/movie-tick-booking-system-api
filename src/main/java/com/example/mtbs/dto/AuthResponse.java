package com.example.mtbs.dto;

import com.example.mtbs.enums.UserRole;
import com.example.mtbs.security.jwt.TokenPayLoad;
import lombok.Builder;

@Builder
public record AuthResponse
        (
                String userId,
                String username,
                String email,
                UserRole role,
                Long accesExpiration,
                Long refreshExpiration,
                TokenPayLoad accessToken,
                TokenPayLoad refreshtoken
        )
{}
