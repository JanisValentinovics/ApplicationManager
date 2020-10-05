package com.petproject.petproject.controller;

import com.petproject.petproject.model.User;
import com.petproject.petproject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String redirectToLogin(){
        return "/auth/login";
    }


    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/user-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:users";
    }

    @GetMapping("user-delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:users";
    }
}