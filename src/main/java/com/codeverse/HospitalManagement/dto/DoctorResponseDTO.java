package com.codeverse.HospitalManagement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DoctorResponseDTO {
    private Long id;
    private String name;
    private String specialization;
    private List<String> departmentName;


    public void setDepartmentNames(List<String> departmentName) {
        this.departmentName = departmentName;
    }
}
