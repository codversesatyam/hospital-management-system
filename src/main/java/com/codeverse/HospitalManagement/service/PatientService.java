package com.codeverse.HospitalManagement.service;

import com.codeverse.HospitalManagement.dto.PatientRequestDTO;
import com.codeverse.HospitalManagement.dto.PatientResponseDTO;

import java.util.List;

public interface PatientService{


    PatientResponseDTO createPatient(PatientRequestDTO dto);

    PatientResponseDTO getPatientById(Long id);

    List<PatientResponseDTO> getAllPatients();

    PatientResponseDTO updatePatient(Long id, PatientRequestDTO dto);

    void deleteById(Long id);

    PatientResponseDTO getPatientByEmail(String email);
}
