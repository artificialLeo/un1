package com.example.universitydeploy.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(generator = "subject_id_generator")
    @GenericGenerator(name = "subject_id_generator", strategy = "increment")
    @Column(name = "subject_id", nullable = false)
    private long subjectId;

    private String subjectName;
    private String subjectManual;

    @ManyToMany(mappedBy = "subjectList")
    private List<Timetable> timetables = new ArrayList<>();

}
