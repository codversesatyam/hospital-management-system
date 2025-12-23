package com.codeverse.HospitalManagement.service;

import com.codeverse.HospitalManagement.Exception.UserNotFoundException;
import com.codeverse.HospitalManagement.dto.PatientRequestDTO;
import com.codeverse.HospitalManagement.dto.PatientResponseDTO;
import com.codeverse.HospitalManagement.entity.Patient;
import com.codeverse.HospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO dto) {
        Patient patient = mapToEntity(dto);
        Patient saved = patientRepository.save(patient);
        return mapToResponseDTO(saved);
    }

    @Override
    public PatientResponseDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("Patient not found with id " + id)
                );
        return mapToResponseDTO(patient);
    }

    @Override
    public List<PatientResponseDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Override
    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO dto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("Patient not found with id " + id)
                );

        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());

        return mapToResponseDTO(patientRepository.save(patient));
    }

    @Override
    public void deleteById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("Patient not found with id " + id)
                );
        patientRepository.delete(patient);
    }

    @Override
    public PatientResponseDTO getPatientByEmail(String email) {
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("Patient not found with email " + email)
                );
        return mapToResponseDTO(patient);
    }



    private Patient mapToEntity(PatientRequestDTO dto) {
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        return patient;
    }

    private PatientResponseDTO mapToResponseDTO(Patient patient) {
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setEmail(patient.getEmail());
        return dto;
    }l
}

