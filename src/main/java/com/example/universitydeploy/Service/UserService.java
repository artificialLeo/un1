package com.example.universitydeploy.Service;

import com.example.universitydeploy.Models.User;
import com.example.universitydeploy.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    public List<User> generateRandomUsers() {
        List<User> users = User.generateRandomUsers();
        return userRepository.saveAll(users);
    }
}
