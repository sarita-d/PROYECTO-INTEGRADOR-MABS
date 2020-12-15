package com.mabs.bookingclinicapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mabs.bookingclinicapi.exceptions.BookingException;
import com.mabs.bookingclinicapi.jsons.ClinicRest;
import com.mabs.bookingclinicapi.responses.BookingResponse;
import com.mabs.bookingclinicapi.services.ClinicService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/booking-clinic" + "/v1")
public class ClinicController {
	
	@Autowired
	ClinicService clinicService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "clinic" + "/{" + "clinicId"
			+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<ClinicRest> getClinicById(@PathVariable Long clinicId) throws BookingException {
		return new BookingResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				clinicService.getClinicById(clinicId));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "clinics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<List<ClinicRest>> getClinics() throws BookingException {
		return new BookingResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK", clinicService.getClinics());
	}

}
