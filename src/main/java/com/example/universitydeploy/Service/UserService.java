package com.example.universitydeploy.Service;

import com.example.universitydeploy.Models.Users;
import com.example.universitydeploy.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void createUser(Users user) {
        userRepository.save(user);
    }

    public void createUserFromList(List<Users> userList) {
        userRepository.saveAll(userList);
    }

    public Users findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public Users findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow();
    }

    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(Users users) {
        Users userById = findUserById(users.getId());
        userById.setEmail(users.getEmail());
        userById.setUserName(users.getUserName());
        userById.setRoles(users.getRoles());
        userRepository.save(userById);
    }

    public void updateUserPassword(Users users) {
        Users userById = findUserById(users.getId());
        userById.setPassword(users.getPassword());
        userRepository.save(userById);
    }

    public void deleteUser(Users user) {
        userRepository.delete(user);
    }
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void saveUser(Users user) {
        userRepository.save(user);
    }
    public void saveAll(List<Users> user) {
        userRepository.saveAll(user);
    }

    public Users loadUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow();
    }

    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);

    }
}
