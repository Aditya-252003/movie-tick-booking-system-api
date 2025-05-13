package com.example.mtbs.dto;

import lombok.Builder;

@Builder
public record AuthResponse
        (
                String userId,
                String username,
                String email,
                String role,
                Long accesExpiration,
                Long refreshExpiration,
                String accessToken,
                String refreshtoken
        )
{}
