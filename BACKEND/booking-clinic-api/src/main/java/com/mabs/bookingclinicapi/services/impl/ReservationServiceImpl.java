package com.mabs.bookingclinicapi.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mabs.bookingclinicapi.entities.Clinic;
import com.mabs.bookingclinicapi.entities.Reservation;
import com.mabs.bookingclinicapi.entities.Specialty;
import com.mabs.bookingclinicapi.exceptions.BookingException;
import com.mabs.bookingclinicapi.exceptions.InternalServerErrorException;
import com.mabs.bookingclinicapi.exceptions.NotFountException;
import com.mabs.bookingclinicapi.jsons.CreateReservationRest;
import com.mabs.bookingclinicapi.jsons.ReservationRest;
import com.mabs.bookingclinicapi.repositories.ClinicRepository;
import com.mabs.bookingclinicapi.repositories.ReservationRespository;
import com.mabs.bookingclinicapi.repositories.SpecialtyRepository;
import com.mabs.bookingclinicapi.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	private ClinicRepository clinicRepository;

	@Autowired
	private SpecialtyRepository specialtyRepository;

	@Autowired
	private ReservationRespository reservationRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	public ReservationRest getReservation(Long reservationId) throws BookingException {
		return modelMapper.map(getReservationEntity(reservationId), ReservationRest.class);
	}

	public String createReservation(final CreateReservationRest createReservationRest) throws BookingException {

		final Clinic clinicId = clinicRepository.findById(createReservationRest.getClinicId())
				.orElseThrow(() -> new NotFountException("CLINIC_NOT_FOUND", "CLINIC_NOT_FOUND"));

		final Specialty specialty = specialtyRepository.findById(createReservationRest.getSpecialtyId())
				.orElseThrow(() -> new NotFountException("SPECIALTY_NOT_FOUND", "SPECIALTY_NOT_FOUND"));

		if (reservationRepository.findBySpecialtyAndClinicId(specialty.getName(), clinicId.getId()).isPresent()) {
			throw new NotFountException("RESERVATION_ALREADT_EXIST", "RESERVATION_ALREADT_EXIST");
		}

		String locator = generateLocator(clinicId, createReservationRest);

		final Reservation reservation = new Reservation();
		reservation.setLocator(locator);
		reservation.setPerson(createReservationRest.getPerson());
		reservation.setDate(createReservationRest.getDate());
		reservation.setClinic(clinicId);
		reservation.setSpecialty(specialty.getName());

		try {
			reservationRepository.save(reservation);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return locator;
	}

	private String generateLocator(final Clinic clinicId, final CreateReservationRest createReservationRest)
			throws BookingException {
		return clinicId.getName() + createReservationRest.getSpecialtyId();
	}

	private Reservation getReservationEntity(Long reservationId) throws BookingException {
		return reservationRepository.findById(reservationId)
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "RESERVATION_NOT_FOUND"));
	}
}
