package com.example.universitydeploy.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "university_group")
public class UniversityGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "university_group_id", nullable = false)
    private Long groupId;

    private String groupName;

    @OneToMany(mappedBy = "group")
    private List<Student> studentList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "universityGroup")
    private List<Timetable> timetableList;

}