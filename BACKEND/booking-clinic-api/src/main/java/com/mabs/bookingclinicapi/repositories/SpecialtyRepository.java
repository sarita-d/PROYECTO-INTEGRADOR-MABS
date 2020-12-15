package com.mabs.bookingclinicapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mabs.bookingclinicapi.entities.Specialty;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
	
	Optional<Specialty> findById(Long id);

}
