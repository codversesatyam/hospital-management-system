package com.codeverse.HospitalManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDTO {

    private String name;
    private String specialization;
    private Long departmentId;
}
