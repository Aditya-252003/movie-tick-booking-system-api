package com.example.mtbs.dto;

import com.example.mtbs.enums.UserRole;

public record UserResponse(String userId ,
                           String username ,
                           String email ,
                           UserRole userRole) {
}
