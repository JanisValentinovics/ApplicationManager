package com.petproject.petproject.repository;

import com.petproject.petproject.model.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
