package lv.team3.botcovidlab.manager.controller;
import lv.team3.botcovidlab.manager.model.Reservation;
import lv.team3.botcovidlab.manager.security.UserDetailsServiceImpl;
import lv.team3.botcovidlab.manager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public String updateReservation(Reservation reservation){
        reservationService.saveReservation(reservation);
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