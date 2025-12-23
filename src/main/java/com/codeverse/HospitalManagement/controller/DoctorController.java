package com.codeverse.HospitalManagement.controller;


import com.codeverse.HospitalManagement.entity.Doctor;
import com.codeverse.HospitalManagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor){
        return ResponseEntity.ok(doctorService.createDoctor(doctor));
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
         return ResponseEntity.ok(doctorService.getDoctorById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id , @RequestBody Doctor doctor){
        return ResponseEntity.ok(doctorService.updateDoctor(id , doctor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id ){
        doctorService.deleteDoctorById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<Doctor>> getDoctorByEmail(@PathVariable String email){
        return ResponseEntity.ok(doctorService.getDoctorByEmail(email));
    }
    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<Doctor>> getDoctorBySpecialization(@PathVariable String specialization){
        return ResponseEntity.ok(doctorService.getDoctorBySpecialization(specialization));
    }
}
