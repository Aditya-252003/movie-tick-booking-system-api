package com.example.mtbs.controller;

import com.example.mtbs.dto.AuthResponse;
import com.example.mtbs.dto.LoginRequest;
import com.example.mtbs.enitity.UserDetails;
import com.example.mtbs.security.jwt.AuthenticatedTokenDetails;
import com.example.mtbs.security.jwt.TokenPayLoad;
import com.example.mtbs.service.AuthService;
import com.example.mtbs.util.ResponseStructure;
import com.example.mtbs.util.RestResponseBuilder;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService service;
    private RestResponseBuilder restResponseBuilder;

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<AuthResponse>> Login(@RequestBody LoginRequest loginRequest){
        AuthResponse authResponse = service.login(loginRequest);
        return restResponseBuilder.success(HttpStatus.OK,"Login Successfully",authResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ResponseStructure<AuthResponse>> refresh(HttpServletRequest request){
        AuthenticatedTokenDetails tokenDetails = (AuthenticatedTokenDetails) request.getAttribute("details");
        AuthResponse authResponse = service.refresh(tokenDetails);
        return restResponseBuilder.success(HttpStatus.OK,"login successfully",authResponse);
    }


}
