package com.mabs.bookingclinicapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mabs.bookingclinicapi.exceptions.BookingException;
import com.mabs.bookingclinicapi.responses.BookingResponse;
import com.mabs.bookingclinicapi.services.CancelReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/booking-clinic" + "/v1")
public class CancelReservationController {

	@Autowired
	CancelReservationService cancelReservationService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/deleteReservation", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<String> deleteReservation(@RequestParam String locator) throws BookingException {
		return new BookingResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				cancelReservationService.deleteReservation(locator));
	}

}
