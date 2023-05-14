package com.foxminded.university.Controller;

import com.foxminded.university.Models.*;
import com.foxminded.university.Service.*;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin-route")
@Controller
@RolesAllowed({"ADMIN"})
public class AdminPageController {

    private final UserService userService;
    private final GroupService groupService;
    private final TeacherService teacherService;
    private final TimetableService timetableService;
    private final SubjectService subjectService;
    private final StudentService studentService;

    @RolesAllowed({"ADMIN"})
    @GetMapping
    public ModelAndView mainPage(Authentication authentication) {
        String username = authentication.getName();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();

        ModelAndView mav = new ModelAndView("admin-page");
        mav.addObject("username", username);
        mav.addObject("authorities", authorities);
        mav.addObject("user", new Users());
        mav.addObject("userList", userService.findAllUsers());

        return mav;
    }

    @RolesAllowed({"ADMIN"})
    @PostMapping("/update-user")
    public ModelAndView updateUser(@ModelAttribute("user") Users user) {
        userService.updateUser(user);
        return new ModelAndView("redirect:/admin-route");
    }

    @RolesAllowed({"ADMIN"})
    @PostMapping("/update-group")
    public ModelAndView updateGroup(@ModelAttribute("group") UniversityGroup group, @ModelAttribute("teacher") Optional<Teacher> teacher) {
        groupService.updateGroup(group, teacher);
        return new ModelAndView("redirect:/group");
    }

    @RolesAllowed({"ADMIN"})
    @PostMapping("/update-timetable")
    public ModelAndView updateTimetable(@ModelAttribute("timetable") Timetable timetable, @RequestParam("subjects") List<Subject> subjects) {
        List<Subject> allSubjects = subjectService.findAll();
        allSubjects.addAll(subjects);

        subjects.forEach(i -> i.setTimetable(timetable));

        subjectService.saveAll(allSubjects);
        return new ModelAndView("redirect:/timetable");
    }

    @RolesAllowed({"ADMIN"})
    @PostMapping("/update-student")
    public ModelAndView updateStudent(@ModelAttribute("student") Student student, @RequestParam("groups") long groupId) {
        UniversityGroup updatedGroup = groupService.findById(groupId).orElseThrow();

        student.setGroup(updatedGroup);
        studentService.save(student);
        return new ModelAndView("redirect:/student");
    }

    @GetMapping("/edit-user/{id}")
    public ModelAndView updateUserMVC(@PathVariable("id") long id) {
        Users userById = userService.findUserById(id);

        ModelAndView userMVC = new ModelAndView("security/user-update");
        userMVC.addObject("userId", userById.getId());
        userMVC.addObject("userEmail", userById.getEmail());
        userMVC.addObject("userName", userById.getUserName());
        userMVC.addObject("rolesValues", Roles.values());

        return userMVC;
    }

    @GetMapping("/edit-group/{id}")
    public ModelAndView updateGroupMVC(@PathVariable("id") long id) {
        UniversityGroup groupById = groupService.findById(id).orElseThrow();

        ModelAndView editedGroupMVC = new ModelAndView("security/group-update");
        editedGroupMVC.addObject("group", groupById);
        editedGroupMVC.addObject("teacher", groupById.getTeacher());

        editedGroupMVC.addObject("teacherList", teacherService.findAll());

        return editedGroupMVC;
    }

    @GetMapping("/edit-timetable/{id}")
    public ModelAndView updateTimetableMVC(@PathVariable("id") long id) {
        Timetable timetableById = timetableService.findById(id).orElseThrow();
        List<Subject> newSubjects = new ArrayList<>();

        ModelAndView editedTimetableMVC = new ModelAndView("security/timetable-update");
        editedTimetableMVC.addObject("timetable", timetableById);
        editedTimetableMVC.addObject("subjectList", subjectService.findAll());

        return editedTimetableMVC;
    }

    @GetMapping("/reassign-student/{id}")
    public ModelAndView reassignTimetableMVC(@PathVariable("id") long id) {
        Student studentById =  studentService.findById(id).orElseThrow();

        ModelAndView editedTimetableMVC = new ModelAndView("security/reassign-student");
        editedTimetableMVC.addObject("student", studentById);
        editedTimetableMVC.addObject("groupList", groupService.findAll());

        return editedTimetableMVC;
    }

    @PostMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteById(id);

        return "redirect:/admin-route";
    }

    @PostMapping("/delete-group/{id}")
    public String deleteGroup(@PathVariable("id") long id) {
        groupService.deleteById(id);

        return "redirect:/group";
    }

    @PostMapping("/delete-timetable/{id}")
    public String deleteTimetable(@PathVariable("id") long id) {
        timetableService.deleteById(id);

        return "redirect:/timetable";
    }

}