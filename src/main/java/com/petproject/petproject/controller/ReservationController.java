package com.petproject.petproject.controller;
import com.petproject.petproject.model.*;
import com.petproject.petproject.security.SecurityUser;
import com.petproject.petproject.security.UserDetailsServiceImpl;
import com.petproject.petproject.service.BookingService;
import com.petproject.petproject.service.ReservationService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class ReservationController {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final ReservationService reservationService;
    private final BookingService bookingService;
    @Autowired
    public ReservationController(UserDetailsServiceImpl userDetailsServiceImpl, ReservationService reservationService, BookingService bookingService) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.reservationService = reservationService;
        this.bookingService = bookingService;
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
         List<Booking> userBookings = bookingService.getAllUserBookings(getCurrentSessionUsername());
           List<Reservation> userReservations = reservationService.findAllUserReservations(userBookings);
        model.addAttribute("reservations",reservations);
        model.addAttribute("bookings", userReservations);
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

    @GetMapping("/apply/{id}")
    @PreAuthorize("hasAuthority('reservations:read')")
    public String makeBooking(@PathVariable("id") Long reservationId ){
        bookingService.saveBooking(bookingService.getNewBooking(reservationId,getCurrentSessionUsername()));
        reservationService.setNotActive(reservationId);
        return "redirect:/reservations";
    }
    @GetMapping("/cancel/{id}")
    @PreAuthorize("hasAuthority('reservations:read')")
    public String cancelBooking(@PathVariable("id") Long reservationId ){
        reservationService.setIsActive(reservationId);
        bookingService.deleteByReservationId(reservationId, getCurrentSessionUsername());
        return "redirect:/reservations";
    }

    private String getCurrentSessionUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername(); }
        else {
            username = principal.toString();
        }
        return username;
    }

}