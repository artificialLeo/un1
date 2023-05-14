package com.foxminded.university.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "teacher")
@DiscriminatorValue("TEACHER")
public class Teacher extends Users {

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UniversityGroup> orders = new ArrayList<>();

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }
}
