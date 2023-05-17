package com.example.universitydeploy.Controller;

import com.example.universitydeploy.Models.Student;
import com.example.universitydeploy.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping(value = "/student")
@Controller
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ModelAndView getStudents(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Student> students = studentService.findAll(pageNo - 1, pageSize);

        ModelAndView modelAndView = new ModelAndView("student");
        modelAndView.addObject("studentList", students.getContent());

        modelAndView.addObject("totalPages", students.getTotalPages());
        modelAndView.addObject("pageSize", pageSize);
        modelAndView.addObject("currentPage", pageNo);

        return modelAndView;
    }


}