package com.codeverse.HospitalManagement.service;

import com.codeverse.HospitalManagement.Exception.UserNotFoundException;
import com.codeverse.HospitalManagement.dto.PatientRequestDTO;
import com.codeverse.HospitalManagement.dto.PatientResponseDTO;
import com.codeverse.HospitalManagement.entity.Patient;
import com.codeverse.HospitalManagement.entity.User;
import com.codeverse.HospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO dto) {

        // 🔑 Get logged-in user from JWT
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Patient patient = mapToEntity(dto);

        // 🔴 THIS WAS MISSING
        patient.setUser(user);

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
        patient.setBirthDate(dto.getBirthDate());
        patient.setGender(dto.getGender());
        patient.setBloodGroup(dto.getBloodGroup());

        return mapToResponseDTO(patient);
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
        patient.setBirthDate(dto.getBirthDate());
        patient.setGender(dto.getGender());
        patient.setBloodGroup(dto.getBloodGroup());
        patient.setPhone(dto.getPhone());
        return patient;
    }

    private PatientResponseDTO mapToResponseDTO(Patient patient) {
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setEmail(patient.getEmail());
        dto.setBirthDate(patient.getBirthDate());
        dto.setGender(patient.getGender());
        dto.setBloodGroup(patient.getBloodGroup());
        dto.setPhone(patient.getPhone());
        return dto;
    }
}

