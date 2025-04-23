package com.example.mtbs.service.impl;

import com.example.mtbs.dto.UserRegistrationRequest;
import com.example.mtbs.dto.UserRequest;
import com.example.mtbs.dto.UserResponse;
import com.example.mtbs.enitity.TheaterOwner;
import com.example.mtbs.enitity.User;
import com.example.mtbs.enitity.UserDetails;
import com.example.mtbs.enums.UserRole;
import com.example.mtbs.repository.UserRepository;
import com.example.mtbs.service.UserMapper;
import com.example.mtbs.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserResponse registerUser(UserRegistrationRequest request) {
        if (request.username() == null || request.username().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (request.email() == null || request.email().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (request.password() == null || request.password().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (request.userRole() == null) {
            throw new IllegalArgumentException("User role is required");
        }

        UserDetails details;
        if (request.userRole() == UserRole.THEATER_OWNER) {
            details = new TheaterOwner();
        } else {
            details = new User();
        }

        details.setUsername(request.username());
        details.setEmail(request.email());
        details.setPassword(request.password());
        details.setPhoneNumber(request.phoneNumber());
        details.setUserRole(request.userRole());

        details.setDateOfBirth(null);
        details.setCreatedAt(null);
        details.setUpdatedAt(null);

         repository.save(details);
        return userMapper.toUserResponse(details);
    }



    @Override
    public UserResponse updateUserProfile(String email, UserRequest request) {
    if{
            UserDetails existingUser =  repository.findByEmail(email);

            if(existingUser==null){
                throw new IllegalArgumentException("User with email " +email+ "not found!");
            }

            existingUser.setUsername(request.username());
            existingUser.setPhoneNumber(request.phoneNumber());
            existingUser.setEmail(request.email());
            existingUser.setPassword(request.password());
            existingUser.setDateOfBirth(request.dateOfBirth());
            existingUser.setUserRole(request.userRole());

            repository.save(existingUser);
            return userMapper.updateUserResponse(existingUser);
        }

    }


}
