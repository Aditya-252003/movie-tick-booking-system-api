package com.example.mtbs.service.impl;

import com.example.mtbs.enitity.TheatreOwner;
import com.example.mtbs.enitity.User;
import com.example.mtbs.enitity.UserDetails;
import com.example.mtbs.exception.UserExistsByEmailException;
import com.example.mtbs.repository.userRepository;
import com.example.mtbs.service.userService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class userServiceImpl implements userService {

    private final userRepository repository;

    @Override
    public UserDetails addUser(UserDetails details) {
        if (!repository.existsByEmail(details.getEmail())) {
            UserDetails userDetails;

            switch (details.getUserRole()) {
                case USER:
                    userDetails = new User();
                    break;
                case THEATRE_OWNER:
                    userDetails = new TheatreOwner();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid user role");
            }

            copyFields(userDetails, details);
            repository.save(userDetails);
            return userDetails;
        } else {
            throw new UserExistsByEmailException("User with this email already exists");
        }
    }



    private UserDetails copyFields(UserDetails target, UserDetails source){
        target.setUserRole(source.getUserRole());
        target.setUserId(source.getUserId());
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
        target.setPhoneNumber(source.getPhoneNumber());
        target.setUsername(source.getUsername());
        target.setCreatedAt(source.getCreatedAt());
        target.setUpdatedAt(source.getUpdatedAt());
        target.setDateOfBirth(source.getDateOfBirth());
        return target;
    }
}
