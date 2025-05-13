package com.example.mtbs.dto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;

public record ExtractToken(
        JwsHeader headers,
        Claims claims
) {
}
