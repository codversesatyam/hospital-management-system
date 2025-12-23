package com.codeverse.HospitalManagement.controller;


import com.codeverse.HospitalManagement.entity.Appointment;
import com.codeverse.HospitalManagement.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor()
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment , Long doctorId , Long patientId){
        return ResponseEntity.ok(appointmentService.createAppointment(appointment , doctorId , patientId));
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id , @RequestBody Appointment appointment){
        return ResponseEntity.ok(appointmentService.updateAppointment(id , appointment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointmentById(id);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentByDoctor(@PathVariable Long id){
        return ResponseEntity.ok(appointmentService.getAppointmentByDoctors(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentByPatient(@PathVariable Long id){
        return ResponseEntity.ok(appointmentService.getAppointmentByPatient(id));
    }

}


