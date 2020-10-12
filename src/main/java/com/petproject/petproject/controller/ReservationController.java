package com.petproject.petproject.controller;
import com.petproject.petproject.model.Reservation;
import com.petproject.petproject.model.Roles;
import com.petproject.petproject.model.Status;
import com.petproject.petproject.model.User;
import com.petproject.petproject.security.SecurityUser;
import com.petproject.petproject.security.UserDetailsServiceImpl;
import com.petproject.petproject.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReservationController {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final ReservationService reservationService;
    @Autowired
    public ReservationController(UserDetailsServiceImpl userDetailsServiceImpl, ReservationService reservationService) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.reservationService = reservationService;
    }

    @GetMapping("/error")
    public String errorProcessing(){
        return "redirect:reservations";
    }

    @GetMapping("/")
    public String redirectToLogin(){
        return "login";
    }


    @GetMapping("/reservations")
    @PreAuthorize("hasAuthority('reservations:read')")
    public String findAll(Model model ){
        List<Reservation> reservations = reservationService.findAll();
        model.addAttribute("reservations",reservations);
        return "reservation-list";
    }

    @GetMapping("/reservation-create")
    @PreAuthorize("hasAuthority('reservations:write')")
    public String createReservationForm(Reservation reservation){
        return "reservation-create";
    }

    @PostMapping("/reservation-create")
    @PreAuthorize("hasAuthority('reservations:write')")
    public String createReservation(Reservation reservation){
        reservationService.saveReservation(reservation);
        return "redirect:reservations";
    }

    @GetMapping("reservation-delete/{id}")
    @PreAuthorize("hasAuthority('reservations:write')")
    public String deleteReservation(@PathVariable("id") Long id){
        reservationService.deleteById(id);
        return "redirect:/reservations";
    }

    @GetMapping("/reservation-update/{id}")
    @PreAuthorize("hasAuthority('reservations:write')")
    public String updateReservationForm(@PathVariable("id") Long id, Model model){
        Reservation reservation = reservationService.findById(id);
        model.addAttribute("reservation", reservation);
        return "reservation-update";
    }

    @PostMapping("/reservation-update")
    @PreAuthorize("hasAuthority('reservations:write')")
    public String updateReservation(Reservation reservation){
        reservationService.saveReservation(reservation);
        return "redirect:reservations";
    }
    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }

    @PostMapping("/create-new-user")
    public String createUser(@ModelAttribute("user") User user){
        user.setRoles(Roles.USER);
        user.setStatus(Status.ACTIVE);
        System.out.println(user.getEmail());
        System.out.println(user.getStatus());
        System.out.println(user.getRoles());
        System.out.println(user.getLastname());
        System.out.println(user.getFirstname());
        userDetailsServiceImpl.saveSimpleUser(user);
        return "redirect:/success";
    }
    @GetMapping("/login")
    public String getSignInPage(){




        return "login";
    }
    @GetMapping("/success")
    public String getSuccessPage(){
        return "success";
    }
}