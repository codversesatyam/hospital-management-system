package com.codeverse.HospitalManagement.service;


import com.codeverse.HospitalManagement.dto.DoctorRequestDTO;
import com.codeverse.HospitalManagement.dto.DoctorResponseDTO;
import com.codeverse.HospitalManagement.entity.Doctor;


import java.util.List;
import java.util.Optional;


public interface DoctorService {

    DoctorResponseDTO createDoctor(DoctorRequestDTO dto);
    DoctorResponseDTO getDoctorById(Long id);
    List<DoctorResponseDTO> getAllDoctors();
    List<DoctorResponseDTO> getDoctorBySpecialization(String specialization);

    Optional<Doctor> getDoctorByEmail(String email);

    void deleteDoctorById(Long id);

    Doctor updateDoctor(Long id, Doctor doctor);
}
