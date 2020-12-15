package com.mabs.bookingclinicapi.services;

import java.util.List;

import com.mabs.bookingclinicapi.exceptions.BookingException;
import com.mabs.bookingclinicapi.jsons.ClinicRest;

public interface ClinicService {
	
ClinicRest getClinicById(Long clinicId) throws BookingException;
	
	public List<ClinicRest> getClinics() throws BookingException;

}
