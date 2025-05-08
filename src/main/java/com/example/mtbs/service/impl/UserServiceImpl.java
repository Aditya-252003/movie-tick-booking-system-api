package com.example.mtbs.service.impl;

import com.example.mtbs.dto.UserRegistrationRequest;
import com.example.mtbs.dto.UserResponse;
import com.example.mtbs.dto.UserUpdationRequest;
import com.example.mtbs.enitity.TheaterOwner;
import com.example.mtbs.enitity.User;
import com.example.mtbs.enitity.UserDetails;
import com.example.mtbs.enums.UserRole;
import com.example.mtbs.mapper.UserDetailsMapper;
import com.example.mtbs.repository.UserRepository;
import com.example.mtbs.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserDetailsMapper userMapper;
    private final PasswordEncoder passwordEncoder;

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
        if (request.userRole().equals(UserRole.THEATER_OWNER)) {
            details = new TheaterOwner();
        } else {
            details = new User();
        }


        details.setUsername(request.username());
        details.setEmail(request.email());
        details.setPassword(passwordEncoder.encode(request.password()));
        details.setPhoneNumber(request.phoneNumber());
        details.setUserRole(request.userRole());
        details.setDateOfBirth(request.dateOfBirth());

         repository.save(details);
        return userMapper.userDetailsResponseMapper(details);
    }


    public UserResponse updateUser(UserUpdationRequest userRequest, String email) {
        if (!repository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email not found in the Database");
        }

        UserDetails user = repository.findByEmail(email);

        if (!email.equals(userRequest.email()) && repository.existsByEmail(userRequest.email())) {
            throw new IllegalArgumentException("User with the email already exists");
        }

        user = copy(user, userRequest);
        repository.save(user);

        return userMapper.userDetailsResponseMapper(user);
    }

    public UserResponse softDeleteUser(String email) {
        if (repository.existsByEmail(email)) {
            UserDetails user = repository.findByEmail(email);
            user.setDelete(true);
            user.setDeletedAt(Instant.now());
            repository.save(user);
            return userMapper.userDetailsResponseMapper(user);
        }
        throw new IllegalArgumentException("Email not found in the Database");
    }

    @Override
    public UserDetails findByemail(String email) {
        return null;
    }

    private UserDetails copyFields(UserDetails userRole, UserRegistrationRequest user) {
        userRole.setUserRole(user.userRole());
        userRole.setPassword(user.password());
        userRole.setEmail(user.email());
        userRole.setDateOfBirth(user.dateOfBirth());
        userRole.setPhoneNumber(user.phoneNumber());
        userRole.setUsername(user.username());
        userRole.setDelete(false);
        repository.save(userRole);
        return userRole;
    }

    private UserDetails copy(UserDetails userRole, UserUpdationRequest user) {
        userRole.setDateOfBirth(user.dateOfBirth());
        userRole.setPhoneNumber(user.phoneNumber());
        userRole.setEmail(user.email());
        userRole.setUsername(user.username());
        userRole.setDelete(false);
        repository.save(userRole);
        return userRole;
    }
}
