package com.petproject.petproject.service;

import com.petproject.petproject.model.Booking;
import com.petproject.petproject.model.Reservation;
import com.petproject.petproject.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation findById(Long id){
        return reservationRepository.getOne(id);
    }

    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }

    public Reservation saveReservation(Reservation reservation){
        reservation.setAvailable(true);
        return reservationRepository.save(reservation);
    }

    public void deleteById(Long id){
        reservationRepository.deleteById(id);
    }

    public Reservation setNotActive(Long id){
        Reservation reservation = reservationRepository.getOne(id);
        reservation.setAvailable(false);
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAllUserReservations(List<Booking> userBookings) {
        List<Reservation> reservationList = new ArrayList<>();
        for (int i =0; i<userBookings.size();i++){
            Booking booking = userBookings.get(i);
            reservationList.add(findById(booking.getReservationId()));
        }
        return reservationList;
    }
    public Reservation setIsActive(Long id){
        Reservation reservation = reservationRepository.getOne(id);
        reservation.setAvailable(true);
        return reservationRepository.save(reservation);
    }
}
