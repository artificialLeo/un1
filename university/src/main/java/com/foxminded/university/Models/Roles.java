package com.foxminded.university.Models;

public enum Roles {
    ADMIN,
    TEACHER,
    STUDENT;

    public String getRoleName() {
        return "ROLE_" + this.name();
    }
}