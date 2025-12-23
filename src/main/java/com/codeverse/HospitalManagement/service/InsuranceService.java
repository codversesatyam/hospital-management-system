package com.codeverse.HospitalManagement.service;

import com.codeverse.HospitalManagement.entity.Insurance;
import com.codeverse.HospitalManagement.entity.Patient;
import com.codeverse.HospitalManagement.repository.InsuranceRepository;
import com.codeverse.HospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance , Long id){
         Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Patient not found with id : " + id)
        );

         patient.setInsurance(insurance);
         insurance.setPatient(patient);


         return patient;

    }

}
