package com.example.universitydeploy.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String password;

    private String userName;

    @Enumerated(EnumType.STRING)
    private Roles roles;

   public Users(String email, String password, String userName, Roles roles) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.roles = roles;
    }

}
