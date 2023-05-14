package com.example.universitydeploy.Controller;

import com.example.universitydeploy.Models.Roles;
import com.example.universitydeploy.Models.Teacher;
import com.example.universitydeploy.Service.GroupService;
import com.example.universitydeploy.Service.TeacherService;
import com.example.universitydeploy.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
