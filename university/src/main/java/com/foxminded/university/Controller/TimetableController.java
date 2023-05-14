package com.foxminded.university.Controller;

import com.foxminded.university.Service.TeacherService;
import com.foxminded.university.Service.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/timetable")
public class TimetableController {
    private final TimetableService timetableService;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("timetable");
        modelAndView.addObject("timetableList", timetableService.findAll());

        return modelAndView;
    }
}
