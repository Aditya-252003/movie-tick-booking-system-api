package com.example.mtbs.security.jwt;

import jakarta.servlet.http.HttpServletRequest;

import java.time.Instant;

public record AuthenticatedTokenDetails(
         String email , String role , Instant tokenExpiration , String currentToken) {
}
