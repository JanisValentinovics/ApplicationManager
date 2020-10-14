package com.petproject.petproject.controller;

import com.petproject.petproject.service.BookingService;
import com.petproject.petproject.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BookingController {


    private final ReservationService reservationService;
    private final BookingService bookingService;
    @Autowired
    public BookingController(ReservationService reservationService, BookingService bookingService) {
        this.reservationService = reservationService;
        this.bookingService = bookingService;
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
