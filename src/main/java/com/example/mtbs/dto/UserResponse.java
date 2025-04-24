package com.example.mtbs.dto;

import com.example.mtbs.enums.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record UserResponse(
        @NotNull
        String userId,
        @NotNull
         @Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$" , message = "Username must be 3-20 characters and can only contain letters, numbers, and underscores")
        String username,
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email format")
        @NotNull String email,
        @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
        @NotNull String phoneNumber,
        @NotNull UserRole userRole

){}
