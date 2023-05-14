package com.example.universitydeploy.Controller;

import com.example.universitydeploy.Service.StudentService;
import com.example.universitydeploy.Service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
