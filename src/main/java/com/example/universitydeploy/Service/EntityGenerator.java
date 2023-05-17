package com.example.universitydeploy.Service;

import com.example.universitydeploy.Models.*;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class EntityGenerator {
    private Faker faker = new Faker();

    private final StudentService studentService;
    private final SubjectService subjectService;
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final TimetableService timetableService;
    private final UserService userService;

    @Transactional
    public void prepareTestData() {
        List<Users> users = new ArrayList<>();

        users.add(new Users("admin@gmail.com", "admin", "Admin user", Roles.ADMIN));
        users.add(new Users("student@gmail.com", "student", "Student user", Roles.STUDENT));
        users.add(new Users("teacher@gmail.com", "teacher", "Teacher user", Roles.TEACHER));

        userService.saveAll(users);

        for (int i = 0; i < 5; i++) {
            Teacher teacher = new Teacher();
            teacher.setEmail("teacher@gmail.com" + i);
            teacher.setPassword("teacher");
            teacher.setUserName("Teacher user" + i);
            teacher.setRoles(Roles.TEACHER);

            teacher.setFirstName(faker.name().firstName());
            teacher.setLastName(faker.name().lastName());
            teacherService.save(teacher);
        }

        for (int i = 0; i < 5; i++) {
            UniversityGroup group = new UniversityGroup();
            group.setGroupName(faker.name().title());
            group.setTeacher(teacherService.findAll().get(randomNumberInRange(1, 4)));
            groupService.save(group);
        }

        for (int i = 0; i < 100; i++) {
            Student student = new Student();
            student.setFirstName(faker.name().firstName());
            student.setLastName(faker.name().lastName());
            student.setGroup(groupService.findAll().get(randomNumberInRange(1, 4)));
            studentService.save(student);
        }

        for (int i = 0; i < 5; i++) {
            Timetable timetable = new Timetable();
            timetable.setUniversityGroup(groupService.findAll().get(randomNumberInRange(1, 4)));
            timetableService.save(timetable);
        }

        for (int i = 0; i < 5; i++) {
            Subject subject = new Subject();
            subject.setSubjectManual(faker.job().field());
            subject.setSubjectName(faker.job().title());

            Timetable timetable = timetableService.findAll().get(randomNumberInRange(1, 4));
            subject.getTimetables().add(timetable);
            timetable.getSubjectList().add(subject);

            subjectService.save(subject);
            timetableService.save(timetable);
        }

        System.out.println("Data generated!");
    }
    private int randomNumberInRange(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}