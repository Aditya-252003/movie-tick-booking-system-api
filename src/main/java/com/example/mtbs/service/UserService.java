package com.example.mtbs.service;


import com.example.mtbs.dto.*;
import jakarta.validation.Valid;

public interface UserService {
    UserResponse registerUser(UserRegistrationRequest registrationRequest);

    UserResponse updateUser(UserUpdationRequest user, String email);

    UserResponse softDeleteUser(String email);

}


