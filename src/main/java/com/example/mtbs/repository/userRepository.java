package com.example.mtbs.repository;

import com.example.mtbs.enitity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<UserDetails, String>{

    boolean existsByEmail(String email);
}
