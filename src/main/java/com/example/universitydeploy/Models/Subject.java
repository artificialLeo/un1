package com.example.universitydeploy.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timetable_id")
    private Timetable timetable;

}
