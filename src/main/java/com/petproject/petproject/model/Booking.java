package com.petproject.petproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "booking", schema = "public")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String userName;
    @Column(name = "reservation_id")
    private Long reservationId;


    public Booking(Long reservationId, String username) {
        this.reservationId=reservationId;
        this.userName=username;
    }
    public Booking() {

    }
}
