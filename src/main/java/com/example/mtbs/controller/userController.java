package com.example.mtbs.controller;

import com.example.mtbs.enitity.UserDetails;
import com.example.mtbs.service.userService;
import com.example.mtbs.util.ResponseStructure;
import com.example.mtbs.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class userController {

    private final userService service;
    private final RestResponseBuilder ResponseBuilder;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserDetails>> addUser(@RequestBody UserDetails details){
        UserDetails userDetails = service.addUser(details);
      return   ResponseBuilder.success(HttpStatus.CREATED,"Added new user Successfully",userDetails);
    }

}
