package com.example.mtbs.security;

import com.example.mtbs.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceimpl implements UserDetailsService {

    private  final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.example.mtbs.enitity.UserDetails user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }


        return User.builder().username(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getUserRole()
                        .name())
                        .build();
    }
}
