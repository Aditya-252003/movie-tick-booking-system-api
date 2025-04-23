package com.example.mtbs.controller;

import com.example.mtbs.dto.UserRegistrationRequest;
import com.example.mtbs.dto.UserRequest;
import com.example.mtbs.dto.UserResponse;
import com.example.mtbs.enitity.UserDetails;
import com.example.mtbs.service.UserService;
import com.example.mtbs.util.ResponseStructure;
import com.example.mtbs.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class userController {

    private final UserService service;
    private final RestResponseBuilder ResponseBuilder;


//    @PostMapping("/register")
//    public ResponseEntity<ResponseStructure<UserDetails>> addUser(@RequestBody UserDetails details){
//        UserDetails userDetails = service.addUser(details);
//      return   ResponseBuilder.success(HttpStatus.CREATED,"Added new user Successfully",userDetails);
//    }


    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> register(@RequestBody UserRegistrationRequest registrationRequest) {
        UserResponse userResponse = service.registerUser(registrationRequest);
        return ResponseBuilder.success(HttpStatus.CREATED, "Added new user Successfully", userResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUserProfile(@RequestParam String email , @RequestBody
    UserRequest request){
       UserResponse updateUser = service.updateUserProfile(email,request);
        return ResponseBuilder.success(HttpStatus.OK,"Updation successfully",updateUser);
    }
}
