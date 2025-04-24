package com.example.mtbs.service;


import com.example.mtbs.dto.UserRegistrationRequest;
import com.example.mtbs.dto.UserResponse;
import com.example.mtbs.dto.UserUpdationRequest;

public interface UserService {
    UserResponse registerUser(UserRegistrationRequest registrationRequest);

    UserResponse updateUser(UserUpdationRequest user, String email);

    UserResponse softDeleteUser(String email);

}


