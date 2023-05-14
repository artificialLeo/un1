package com.example.universitydeploy.Controller;

import com.example.universitydeploy.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ModelAndView getAllUsers(Model model) {
        ModelAndView modelAndView = new ModelAndView("user-list");
        userService.generateRandomUsers();

        model.addAttribute("users", userService.retrieveAllUsers());
        return modelAndView;
    }
}
