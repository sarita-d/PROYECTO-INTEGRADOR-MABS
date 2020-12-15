package com.mabs.bookingclinicapi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mabs.bookingclinicapi.exceptions.BookingException;
import com.mabs.bookingclinicapi.exceptions.NotFountException;
import com.mabs.bookingclinicapi.entities.Clinic;
import com.mabs.bookingclinicapi.jsons.ClinicRest;
import com.mabs.bookingclinicapi.repositories.ClinicRepository;
import com.mabs.bookingclinicapi.services.ClinicService;

@Service
public class ClinicServiceImpl implements ClinicService {

	@Autowired
	ClinicRepository clinicRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	public ClinicRest getClinicById(Long clinicId) throws BookingException {
		return modelMapper.map(getClinicEntity(clinicId), ClinicRest.class);
	}

	public List<ClinicRest> getClinics() throws BookingException {
		final List<Clinic> ClinicsEntity = clinicRepository.findAll();
		return ClinicsEntity.stream().map(service -> modelMapper.map(service, ClinicRest.class))
				.collect(Collectors.toList());
	}

	private Clinic getClinicEntity(Long clinicId) throws BookingException {
		return clinicRepository.findById(clinicId)
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "CLINIC_NOT_FOUND"));
	}

}
