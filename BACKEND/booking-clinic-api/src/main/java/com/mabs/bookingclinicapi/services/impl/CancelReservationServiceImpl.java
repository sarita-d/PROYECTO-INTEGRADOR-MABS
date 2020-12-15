package com.mabs.bookingclinicapi.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mabs.bookingclinicapi.exceptions.BookingException;
import com.mabs.bookingclinicapi.exceptions.InternalServerErrorException;
import com.mabs.bookingclinicapi.exceptions.NotFountException;
import com.mabs.bookingclinicapi.repositories.ReservationRespository;
import com.mabs.bookingclinicapi.services.CancelReservationService;

@Service
public class CancelReservationServiceImpl implements CancelReservationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CancelReservationServiceImpl.class);

	@Autowired
	private ReservationRespository reservationRespository;

	public String deleteReservation(String locator) throws BookingException {

		reservationRespository.findByLocator(locator)
				.orElseThrow(() -> new NotFountException("LOCATOR_NOT_FOUND", "LOCATOR_NOT_FOUND"));

		try {
			reservationRespository.deleteByLocator(locator);
		} catch (Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}

		return "LOCATOR_DELETED";
	}

}
