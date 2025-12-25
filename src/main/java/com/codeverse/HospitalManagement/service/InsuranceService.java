package com.codeverse.HospitalManagement.service;

import com.codeverse.HospitalManagement.entity.Insurance;
import com.codeverse.HospitalManagement.entity.Patient;
import com.codeverse.HospitalManagement.repository.InsuranceRepository;
import com.codeverse.HospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface InsuranceService {

    Patient assignedInsuranceToPatient(Insurance insurance , Long patientId);


}
