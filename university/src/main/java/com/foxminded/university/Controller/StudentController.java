package com.foxminded.university.Controller;

import com.foxminded.university.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping(value = "/student")
@Controller
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("student");
        modelAndView.addObject("studentList", studentService.findAll());

        return modelAndView;
    }

}