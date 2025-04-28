package com.example.mtbs.controller;

import com.example.mtbs.dto.*;
import com.example.mtbs.service.UserService;
import com.example.mtbs.util.ResponseStructure;
import com.example.mtbs.util.RestResponseBuilder;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class UserController {

    private final UserService service;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> register(@RequestBody @Valid UserRegistrationRequest registrationRequest) {
        UserResponse userResponse = service.registerUser(registrationRequest);
        return responseBuilder.success(HttpStatus.CREATED, "Added new user Successfully", userResponse);
    }

    @PutMapping("/users/{email}")
    public ResponseEntity<ResponseStructure<UserResponse>> editUser(@PathVariable String email, @RequestBody UserUpdationRequest user){
        UserResponse userDetails = service.updateUser(user, email);
        return responseBuilder.success(HttpStatus.OK,"User Details updated", userDetails);
    }

    @DeleteMapping("/users/{email}")
    public ResponseEntity<ResponseStructure<UserResponse>> softDeleteUser(@PathVariable String email){
        UserResponse userDetails = service.softDeleteUser(email);
        return responseBuilder.success(HttpStatus.OK,"UserDetails account deleted ", userDetails);
    }
}
