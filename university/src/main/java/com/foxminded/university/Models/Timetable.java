package com.foxminded.university.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


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

    @OneToMany(mappedBy = "timetable")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Subject> subjectList;

}
