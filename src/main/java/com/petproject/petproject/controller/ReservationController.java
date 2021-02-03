package com.petproject.petproject.controller;

import com.petproject.petproject.model.Reservation;
import com.petproject.petproject.security.UserDetailsServiceImpl;
import com.petproject.petproject.service.ReservationService;
import com.petproject.petproject.service.entityManager.FirebaseService;
import com.petproject.petproject.service.entityManager.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
    public String findAll(Model model ) throws ExecutionException, InterruptedException {
        List<Patient> reservations = FirebaseService.getAllPatientsDetails();

        model.addAttribute("reservations",reservations);
        return "reservation-list";
    }

    @GetMapping("/reservation-create")
    @PreAuthorize("hasAuthority('reservations:write')")
    public String createReservationForm(Patient patient){
        return "reservation-create";
    }

    @PostMapping("/reservation-create")
    @PreAuthorize("hasAuthority('reservations:write')")
    public String createReservation(Patient patient) throws ExecutionException, InterruptedException {
        FirebaseService.savePatientDetails(patient);
        return "redirect:reservations";
    }

    @GetMapping("reservation-delete/{id}")
    @PreAuthorize("hasAuthority('reservations:write')")
    public String deleteReservation(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        FirebaseService.deletePatient(id);
        return "redirect:/reservations";
    }

    @GetMapping("/reservation-update/{id}")
    @PreAuthorize("hasAuthority('reservations:write')")
    public String updateReservationForm(@PathVariable("id") String id, Model model) throws ExecutionException, InterruptedException {
        Patient reservation = FirebaseService.findByPersonaId(id);
        model.addAttribute("reservation", reservation);
        return "reservation-update";
    }

    @PostMapping("/reservation-update")
    public String updateReservation(Patient patient) throws ExecutionException, InterruptedException {
        System.out.println(patient);
        FirebaseService.updatePatientDetails(patient);
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