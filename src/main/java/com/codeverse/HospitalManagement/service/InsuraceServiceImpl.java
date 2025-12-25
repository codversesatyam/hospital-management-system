package com.codeverse.HospitalManagement.service;

import com.codeverse.HospitalManagement.entity.Insurance;
import com.codeverse.HospitalManagement.entity.Patient;
import com.codeverse.HospitalManagement.repository.InsuranceRepository;
import com.codeverse.HospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuraceServiceImpl implements InsuranceService{

    public final PatientRepository patientRepository;

    public final InsuranceRepository insuranceRepository;

    @Override
    public Patient assignedInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Patient not found with id : " + patientId));

        patient.setInsurance(insurance);
        insurance.setPatient(patient);


        insuranceRepository.save(insurance);

        return patient;
    }
}
