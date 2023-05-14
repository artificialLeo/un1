package com.example.universitydeploy.Service;

import com.example.universitydeploy.Models.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users userByEmail = userService.findUserByEmail(username);

        if (userByEmail == null) {
            throw new UsernameNotFoundException("Unknown user: " + username);
        }

        return User.builder()
                .username(userByEmail.getEmail())
                .password(userByEmail.getPassword())
                .roles(userByEmail.getRoles().toString())
                .build();
    }
}
