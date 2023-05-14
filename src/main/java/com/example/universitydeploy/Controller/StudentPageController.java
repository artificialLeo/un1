package com.example.universitydeploy.Controller;

import com.example.universitydeploy.Models.Users;
import com.example.universitydeploy.Service.UserService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/student-route")
@Controller
@RolesAllowed({"STUDENT"})
public class StudentPageController {

    private final UserService userService;
    @GetMapping
    public ModelAndView mainPage(Authentication authentication) {
        String username = authentication.getName();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();

        Users userByEmail = userService.loadUserByEmail(username);

        ModelAndView mav = new ModelAndView("student-page");
        mav.addObject("username", username);
        mav.addObject("authorities", authorities);
        mav.addObject("userID", userByEmail.getId());

        return mav;
    }

    @PostMapping("/update-user-email")
    public ModelAndView updateUser(@ModelAttribute("user") Users user) {
        System.out.println(user);
        userService.updateUserPassword(user);

        return new ModelAndView("redirect:/student-route");
    }

    @GetMapping("/edit-user/{id}")
    public ModelAndView updateUserMVC(@PathVariable("id") long id) {
        Users userById = userService.findUserById(id);
        ModelAndView userMVC = new ModelAndView("security/user-update-pass");
        userMVC.addObject("user", userById);

        return userMVC;
    }
}