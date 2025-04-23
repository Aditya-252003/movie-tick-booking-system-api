package com.example.mtbs.service;


import com.example.mtbs.dto.UserRegistrationRequest;
import com.example.mtbs.dto.UserRequest;
import com.example.mtbs.dto.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRegistrationRequest registrationRequest);

    UserResponse updateUserProfile(String email, UserRequest request);


//    UserDetails addUser(UserDetails details);

}


