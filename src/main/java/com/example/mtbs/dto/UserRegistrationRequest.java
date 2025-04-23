package com.example.mtbs.dto;


import com.example.mtbs.enums.UserRole;

public record UserRegistrationRequest(String username ,
                                      String email ,
                                      String password ,
                                      String phoneNumber ,
                                      UserRole userRole) {
}
