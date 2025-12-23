package com.codeverse.HospitalManagement.service;

import com.codeverse.HospitalManagement.entity.Appointment;
import com.codeverse.HospitalManagement.entity.Doctor;
import com.codeverse.HospitalManagement.entity.Patient;
import com.codeverse.HospitalManagement.repository.AppointmentRepository;
import com.codeverse.HospitalManagement.repository.DoctorRepository;
import com.codeverse.HospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;


    @Transactional
    public Appointment createAppointment(Appointment appointment , Long doctorId , Long patientId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have ID");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointmentRepository.save(appointment);

        patient.getAppointments().add(appointment);

        return appointment;
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id){
        return appointmentRepository.findById(id);
    }

    public Appointment updateAppointment(Long id , Appointment updatedAppoint){
        return appointmentRepository.findById(id).map(
                appointment -> {
                    appointment.setAppointmentDate(updatedAppoint.getAppointmentDate());
                    appointment.setDoctor(updatedAppoint.getDoctor());
                    appointment.setReason(updatedAppoint.getReason());
                    return appointmentRepository.save(appointment);
                }
        ).orElseThrow(() -> new RuntimeException("Appointment not found!"));
    }

    public void deleteAppointmentById(Long id){
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> getAppointmentByDoctors(Long id){
        return appointmentRepository.findByDoctorId(id);
    }

    public List<Appointment> getAppointmentByPatient(Long id){
        return appointmentRepository.findByPatientId(id);
    }
}

