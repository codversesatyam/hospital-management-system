package com.codeverse.HospitalManagement.service;

import com.codeverse.HospitalManagement.entity.Appointment;


import java.util.List;
import java.util.Optional;


public interface AppointmentService {

    Appointment createAppointment( Appointment appointment , Long doctorId , Long patientId);

    List<Appointment> getAllAppointments();

    Optional<Appointment> getAppointmentById(Long id);

    Appointment updateAppointment(Long id , Appointment updateAppointment);

    void deleteAppointmentById(Long id);

    List<Appointment> getAppointmentByDoctors(Long doctorId);


    List<Appointment> getAppointmentByPatient(Long patientId);


    }

