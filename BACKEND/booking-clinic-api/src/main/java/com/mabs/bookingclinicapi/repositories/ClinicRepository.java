package com.mabs.bookingclinicapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mabs.bookingclinicapi.entities.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, Long>{
	
	Optional<Clinic> findById(Long id);
	
	Optional<Clinic> findByName(String nameClinic);
	
	@Query("SELECT REST FROM Clinic REST")
	public List<Clinic> findClinics();

}
