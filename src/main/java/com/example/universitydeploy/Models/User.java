package com.example.universitydeploy.Models;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Data
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;


    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static List<User> generateRandomUsers() {
        List<User> users = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("User " + (i + 1));
            user.setEmail("user" + (i + 1) + "@example.com");
            users.add(user);
        }
        return users;
    }

}
