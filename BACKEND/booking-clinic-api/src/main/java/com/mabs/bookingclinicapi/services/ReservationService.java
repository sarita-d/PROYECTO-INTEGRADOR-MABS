package com.mabs.bookingclinicapi.services;

import com.mabs.bookingclinicapi.exceptions.BookingException;
import com.mabs.bookingclinicapi.jsons.CreateReservationRest;
import com.mabs.bookingclinicapi.jsons.ReservationRest;

public interface ReservationService {
	
	ReservationRest getReservation(Long reservationId) throws BookingException;
	
	String createReservation(CreateReservationRest CreateReservationRest) throws BookingException;

}
