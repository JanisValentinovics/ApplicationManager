package lv.team3.botcovidlab.manager.repository;

import lv.team3.botcovidlab.manager.model.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
