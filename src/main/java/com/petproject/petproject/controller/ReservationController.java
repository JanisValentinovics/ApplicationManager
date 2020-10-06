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


    @GetMapping("/reservations")
    public String findAll(Model model){
        List<Reservation> reservations = reservationService.findAll();
        model.addAttribute("reservations",reservations);
        return "reservation-list";
    }

    @GetMapping("/reservation-create")
    @PreAuthorize("hasAuthority('users:write')")
    public String createUserForm(Reservation reservation){
        return "reservation-create";
    }

    @PostMapping("/reservation-create")
    @PreAuthorize("hasAuthority('users:write')")
    public String createUser(Reservation reservation){
        reservationService.saveUser(reservation);
        return "redirect:reservations";
    }

    @GetMapping("reservation-delete/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String deleteUser(@PathVariable("id") Long id){
        reservationService.deleteById(id);
        return "redirect:/reservations";
    }

    @GetMapping("/reservation-update/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        Reservation reservation = reservationService.findById(id);
        model.addAttribute("user", reservation);
        return "reservation-update";
    }

    @PostMapping("/reservation-update")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateUser(Reservation reservation){
        reservationService.saveUser(reservation);
        return "redirect:reservations";
    }
}