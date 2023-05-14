package com.foxminded.university.Controller;

import com.foxminded.university.Service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("subject");
        modelAndView.addObject("subjectList", subjectService.findAll());

        return modelAndView;
    }

}
