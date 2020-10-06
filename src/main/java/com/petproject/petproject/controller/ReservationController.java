package com.petproject.petproject.controller;

import com.petproject.petproject.model.Reservation;
import com.petproject.petproject.service.ReservationService;

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
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public String redirectToLogin(){
        return "/auth/login";
    }


    @GetMapping("/users")
    public String findAll(Model model){
        List<Reservation> reservations = reservationService.findAll();
        model.addAttribute("users", reservations);
        return "user-list";
    }

    @GetMapping("/user-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createUserForm(Reservation reservation){
        return "user-create";
    }

    @PostMapping("/user-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createUser(Reservation reservation){
        reservationService.saveUser(reservation);
        return "redirect:users";
    }

    @GetMapping("user-delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String deleteUser(@PathVariable("id") Long id){
        reservationService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        Reservation reservation = reservationService.findById(id);
        model.addAttribute("user", reservation);
        return "user-update";
    }

    @PostMapping("/user-update")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateUser(Reservation reservation){
        reservationService.saveUser(reservation);
        return "redirect:users";
    }
}