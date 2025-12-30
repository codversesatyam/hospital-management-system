package com.codeverse.HospitalManagement.dto;

import com.codeverse.HospitalManagement.Enum.BloodGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthdate;
    private String gender;
    private BloodGroup bloodGroup;

    public void setBirthDate(LocalDate birthDate) {
        this.birthdate = birthDate;
    }
}
