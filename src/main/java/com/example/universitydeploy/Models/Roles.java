package com.example.universitydeploy.Models;

public enum Roles {
    ADMIN,
    TEACHER,
    STUDENT;

    public String getRoleName() {
        return "ROLE_" + this.name();
    }
}