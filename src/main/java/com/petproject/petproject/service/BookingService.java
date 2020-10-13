package com.petproject.petproject.service;
import com.petproject.petproject.model.Booking;
import com.petproject.petproject.model.Reservation;
import com.petproject.petproject.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
//    public List<Booking> getUserBookings(Long id){
//        return bookingRepository;
//    }
    public Booking saveBooking(Booking booking){
       return bookingRepository.save(booking);
    }
    public void deleteById(Long id){
        bookingRepository.deleteById(id);
    }
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }


    public Booking getNewBooking(Long reservationId, String username) {
        return new Booking(reservationId, username);
    }

    public List<Booking> getAllUserBookings(String username) {
        return bookingRepository.findByUserName(username);}

    public void deleteByReservationId(Long reservationId, String username) {
        List<Booking> bookingList = getAllUserBookings(username);
        for (int i =0; i<bookingList.size();i++){
            Booking booking = bookingList.get(i);
            if (booking.getReservationId().equals(reservationId)){
                bookingRepository.deleteById(booking.getId());
            }
        }
    }
}
