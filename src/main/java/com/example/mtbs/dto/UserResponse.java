package com.example.mtbs.dto;

import com.example.mtbs.enums.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record UserResponse(
        String userId,
        String username,
        String email,
        String phoneNumber,
        UserRole userRole

) {
}
