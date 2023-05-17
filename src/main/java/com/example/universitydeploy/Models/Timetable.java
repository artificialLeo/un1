package com.example.universitydeploy.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "timetable")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "timetable_id", nullable = false)
    private long timetableId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_group_id")
    private UniversityGroup universityGroup;

    @ManyToMany
    @JoinTable(
            name = "group_subject",
            joinColumns = @JoinColumn(name = "timetable_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjectList = new ArrayList<>();

}
