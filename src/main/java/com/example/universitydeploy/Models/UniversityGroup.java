package com.example.universitydeploy.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @OneToMany(mappedBy = "group", cascade=CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Student> studentList;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
    @JoinColumn(name = "teacher_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Teacher teacher;

    @OneToMany(mappedBy = "universityGroup", cascade=CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Timetable> timetableList;

}