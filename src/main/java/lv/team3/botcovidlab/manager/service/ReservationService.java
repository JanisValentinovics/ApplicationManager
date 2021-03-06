package lv.team3.botcovidlab.manager.service;

import lv.team3.botcovidlab.manager.model.Reservation;
import lv.team3.botcovidlab.manager.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public Reservation setIsActive(Long id){
        Reservation reservation = reservationRepository.getOne(id);
        reservation.setAvailable(true);
        return reservationRepository.save(reservation);
    }
}
