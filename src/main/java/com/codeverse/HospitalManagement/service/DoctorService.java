package com.codeverse.HospitalManagement.service;


import com.codeverse.HospitalManagement.dto.LoginReponseDTO;
import com.codeverse.HospitalManagement.dto.LoginRequestDTO;
import com.codeverse.HospitalManagement.entity.Doctor;
import com.codeverse.HospitalManagement.entity.User;
import com.codeverse.HospitalManagement.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public Doctor createDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id ){
        return doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor Not Found"));
    }

    public Doctor updateDoctor(Long id , Doctor updatedDoctor){
        return doctorRepository.findById(id).map(
                doctor -> {
                    doctor.setName(updatedDoctor.getName());
                    doctor.setEmail(updatedDoctor.getEmail());
                    return doctorRepository.save(doctor);
                }
        ).orElseThrow(() -> new RuntimeException("Doctor Not Found !"));
    }

    public void deleteDoctorById(Long id ){
        doctorRepository.deleteById(id);
    }

    public Optional<Doctor> getDoctorByEmail(String email){

        return doctorRepository.findByEmail(email);

    }

    public List<Doctor> getDoctorBySpecialization(String specialization){

        return doctorRepository.findBySpecialization(specialization);

    }

    @Service
    @RequiredArgsConstructor
    public static class AuthService {
        private final AuthenticationManager authenticationManager;
        public LoginReponseDTO login(LoginRequestDTO loginRequestDTO) {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUserName() , loginRequestDTO.getPassword() )
            );
            User user = (User) authentication.getPrincipal();

        }
    }
}
