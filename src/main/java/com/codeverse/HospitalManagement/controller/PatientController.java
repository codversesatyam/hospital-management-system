package com.codeverse.HospitalManagement.controller;

import com.codeverse.HospitalManagement.dto.PatientRequestDTO;
import com.codeverse.HospitalManagement.dto.PatientResponseDTO;
import com.codeverse.HospitalManagement.entity.Patient;
import com.codeverse.HospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@RequestBody PatientRequestDTO patientRequestDTO){
        return ResponseEntity.ok(patientService.createPatient(patientRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Long id , @RequestBody PatientRequestDTO patientRequestDTO){
        return ResponseEntity.ok(patientService.updatePatient(id , patientRequestDTO));
    }

    @DeleteMapping("/{id}")
    public void deletePatientById(@PathVariable Long id ){
        patientService.deleteById(id);
    }

    @GetMapping("/email/{email}")
    public PatientResponseDTO getPatientByEmail(@PathVariable String email){
        return patientService.getPatientByEmail(email);
    }
}

