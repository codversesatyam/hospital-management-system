package com.codeverse.HospitalManagement.repository;

import com.codeverse.HospitalManagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByEmail(String email); // safer with Optional
}
