package com.foxminded.university.Controller;

import com.foxminded.university.Models.Roles;
import com.foxminded.university.Service.UserService;
import com.foxminded.university.Models.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class SecurityController {

    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("security/login");
    }

    @GetMapping("/register")
    public ModelAndView registerMVC() {
        return new ModelAndView("security/register");
    }

    @PostMapping("/reg")
    public ModelAndView addUser(@ModelAttribute(name = "users") Users users) {
        users.setRoles(Roles.STUDENT);
        userService.createUser(users);

        return new ModelAndView("security/login");
    }
}
