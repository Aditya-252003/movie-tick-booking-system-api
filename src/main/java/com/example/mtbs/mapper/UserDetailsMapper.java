package com.example.mtbs.mapper;


import com.example.mtbs.dto.UserResponse;
import com.example.mtbs.enitity.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {


    public UserResponse userDetailsResponseMapper(UserDetails userDetails){
        if(userDetails == null)
            return null;
        return new UserResponse(
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getPhoneNumber(),
                userDetails.getUserRole()
        );
    }

}

