package com.foxminded.university.Controller;

import com.foxminded.university.Models.Roles;
import com.foxminded.university.Models.Teacher;
import com.foxminded.university.Models.UniversityGroup;
import com.foxminded.university.Models.Users;
import com.foxminded.university.Service.GroupService;
import com.foxminded.university.Service.TeacherService;
import com.foxminded.university.Service.UserService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping(value = "/group")
@Controller
public class GroupController {
    private final UserService userService;
    private final GroupService groupService;
    private final TeacherService teacherService;

    @GetMapping
    public ModelAndView getAll(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("group");

        Teacher user = null;
        if (authentication.getAuthorities().stream().findFirst().orElseThrow().getAuthority().equals(Roles.TEACHER.getRoleName())) {
            user = teacherService.findByEmail(authentication.getName());
        }

        if (user != null) {
            modelAndView.addObject("groupList", groupService.findAllByID(user.getId()));
        } else {
            modelAndView.addObject("groupList", groupService.findAll());
        }

        modelAndView.addObject("teachers", teacherService.findAll());

        return modelAndView;
    }
}
