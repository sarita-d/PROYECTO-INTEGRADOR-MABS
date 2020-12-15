package com.mabs.bookingclinicapi.services;

import com.mabs.bookingclinicapi.exceptions.BookingException;

public interface CancelReservationService {
	
	public String deleteReservation(String locator) throws BookingException;

}
