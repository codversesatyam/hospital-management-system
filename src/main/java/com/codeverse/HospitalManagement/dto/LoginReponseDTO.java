package com.codeverse.HospitalManagement.dto;

import lombok.Data;

@Data
public class LoginReponseDTO {

    String jwt;
    Long userId;
}
