package com.codeverse.HospitalManagement.dto;

import com.codeverse.HospitalManagement.Enum.BloodGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDTO {

    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String gender;
    private BloodGroup bloodGroup;


}
