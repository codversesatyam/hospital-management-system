package com.codeverse.HospitalManagement.service;

import com.codeverse.HospitalManagement.Exception.UserNotFoundException;
import com.codeverse.HospitalManagement.dto.DoctorRequestDTO;
import com.codeverse.HospitalManagement.dto.DoctorResponseDTO;
import com.codeverse.HospitalManagement.entity.Department;
import com.codeverse.HospitalManagement.entity.Doctor;
import com.codeverse.HospitalManagement.repository.DepartmentRepository;
import com.codeverse.HospitalManagement.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public DoctorResponseDTO createDoctor(DoctorRequestDTO dto) {
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() ->
                        new UserNotFoundException("Department not found with id " + dto.getDepartmentId())
                );

        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setDepartment(department);

        return mapToDTO(doctorRepository.save(doctor));
    }

    @Override
    public DoctorResponseDTO getDoctorById(Long id) {
        return mapToDTO(getDoctorEntityById(id));
    }

    @Override
    public List<DoctorResponseDTO> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<DoctorResponseDTO> getDoctorBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public Optional<Doctor> getDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }


    @Override
    public void deleteDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("Doctor not found with id " + id)
                );
        doctorRepository.delete(doctor);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {

        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("Doctor not found with id " + id)
                );

        existingDoctor.setName(updatedDoctor.getName());
        existingDoctor.setEmail(updatedDoctor.getEmail());
        existingDoctor.setSpecialization(updatedDoctor.getSpecialization());
        existingDoctor.setPhoneNo(updatedDoctor.getPhoneNo());

        return doctorRepository.save(existingDoctor);
    }



    private Doctor getDoctorEntityById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("Doctor not found with id " + id)
                );
    }

    private DoctorResponseDTO mapToDTO(Doctor doctor) {
        DoctorResponseDTO dto = new DoctorResponseDTO();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setSpecialization(doctor.getSpecialization());

        List<String> deptNames = doctor.getDepartments()
                .stream()
                .map(Department::getName)
                .toList();

        dto.setDepartmentNames(deptNames);
        return dto;
    }



}
