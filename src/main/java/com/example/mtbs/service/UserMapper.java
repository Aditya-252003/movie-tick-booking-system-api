package com.example.mtbs.service;

import com.example.mtbs.dto.UserRegistrationRequest;
import com.example.mtbs.dto.UserResponse;
import com.example.mtbs.enitity.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public  UserResponse toUserResponse(UserDetails userDetails){
        return new UserResponse(
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getPhoneNumber(),
                userDetails.getEmail(),
                userDetails.getDateOfBirth(),
                userDetails.getUpdatedAt()
                userDetails.getUserRole());
    }

    public UserResponse updateUserResponse(UserDetails details){
        return new UserResponse(
                details.getUsername(),
                details.getPhoneNumber(),
                details.getEmail(),
                details.getPassword(),
                details.getDateOfBirth()
                details.getUserRole()
        );
    }
}
