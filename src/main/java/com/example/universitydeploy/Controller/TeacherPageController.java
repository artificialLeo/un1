package com.example.universitydeploy.Controller;

import com.example.universitydeploy.Models.*;
import com.example.universitydeploy.Service.GroupService;
import com.example.universitydeploy.Service.StudentService;
import com.example.universitydeploy.Service.SubjectService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/teacher-route")
@Controller
@RolesAllowed({"TEACHER"})
public class TeacherPageController {
    private final StudentService studentService;
    private final GroupService groupService;
    private final SubjectService subjectService;

    @GetMapping
    public ModelAndView mainPage(Authentication authentication) {
        String username = authentication.getName();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();

        ModelAndView mav = new ModelAndView("teacher-page");
        mav.addObject("username", username);
        mav.addObject("authorities", authorities);

        return mav;
    }

    @GetMapping("/add-student-redirect")
    public ModelAndView addStudentMVC() {
        ModelAndView modelAndView = new ModelAndView("security/add-student");
        Student student = new Student();
        modelAndView.addObject("student", new Student());

        List<Long> allGroupIDs = groupService.findAll()
                .stream()
                .map(UniversityGroup::getGroupId)
                .toList();

        modelAndView.addObject("groupIDs", allGroupIDs);

        return modelAndView;
    }

    @PostMapping("/add-student")
    public ModelAndView addStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);

        return new ModelAndView("redirect:/student");
    }

    @GetMapping("/edit-subject/{id}")
    public ModelAndView updateGroupMVC(@PathVariable("id") long id) {
        Subject subjectById = subjectService.findById(id).orElseThrow();

        ModelAndView editedGroupMVC = new ModelAndView("security/subject-update");
        editedGroupMVC.addObject("subject", subjectById);

        return editedGroupMVC;
    }

    @PostMapping("/update-subject")
    public ModelAndView updateGroup(@ModelAttribute("subject") Subject subject) {
        subjectService.save(subject);
        return new ModelAndView("redirect:/subject");
    }

    @PostMapping("/delete-student/{id}")
    public String deleteTimetable(@PathVariable("id") long id) {
        studentService.deleteById(id);

        return "redirect:/student";
    }

    @PostMapping("/delete-subject/{id}")
    public String deleteSubject(@PathVariable("id") long id) {
        subjectService.deleteById(id);

        return "redirect:/subject";
    }


}