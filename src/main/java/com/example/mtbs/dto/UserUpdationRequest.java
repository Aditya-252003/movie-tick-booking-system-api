package com.example.mtbs.dto;


import com.example.mtbs.enums.UserRole;

import java.time.LocalDate;

public record UserUpdationRequest(
        String username,
        String email,
        String phoneNumber,
        LocalDate dateOfBirth
)
{}
