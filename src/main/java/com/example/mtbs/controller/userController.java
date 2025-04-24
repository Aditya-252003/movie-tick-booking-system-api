package com.example.mtbs.controller;

import com.example.mtbs.dto.UserRegistrationRequest;
import com.example.mtbs.dto.UserResponse;
import com.example.mtbs.dto.UserUpdationRequest;
import com.example.mtbs.enitity.UserDetails;
import com.example.mtbs.service.UserService;
import com.example.mtbs.util.ResponseStructure;
import com.example.mtbs.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class userController {

    private final UserService service;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> register(@RequestBody @Validated UserRegistrationRequest registrationRequest) {
        UserResponse userResponse = service.registerUser(registrationRequest);
        return responseBuilder.success(HttpStatus.CREATED, "Added new user Successfully", userResponse);
    }

    @PutMapping("/users/{email}")
    public ResponseEntity<ResponseStructure<UserResponse>> editUser(@PathVariable String email, @RequestBody UserUpdationRequest user){
        UserResponse userDetails = service.updateUser(user, email);
        return responseBuilder.success(HttpStatus.OK,"User Details has been updated", userDetails);
    }

    @DeleteMapping("/users/{email}")
    public ResponseEntity<ResponseStructure<UserResponse>> softDeleteUser(@PathVariable String email){
        UserResponse userDetails = service.softDeleteUser(email);
        return responseBuilder.success(HttpStatus.OK,"UserDetails account has been deleted ", userDetails);
    }
}
