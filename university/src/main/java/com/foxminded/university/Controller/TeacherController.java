package com.foxminded.university.Controller;

import com.foxminded.university.Models.Roles;
import com.foxminded.university.Models.Student;
import com.foxminded.university.Models.Timetable;
import com.foxminded.university.Models.Users;
import com.foxminded.university.Service.StudentService;
import com.foxminded.university.Service.SubjectService;
import com.foxminded.university.Service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
    private final TeacherService teacherService;
    private final StudentService studentService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("teacher");
        modelAndView.addObject("teacherList", teacherService.findAll());

        return modelAndView;
    }
}
