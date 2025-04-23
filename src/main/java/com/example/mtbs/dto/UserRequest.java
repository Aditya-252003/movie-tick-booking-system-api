package com.example.mtbs.dto;


import com.example.mtbs.enums.UserRole;

import java.time.LocalDate;

public record UserRequest(String username ,
                          String phoneNumber ,
                          String email ,
                          String password,
                          LocalDate dateOfBirth,
                            UserRole userRole) {
}
