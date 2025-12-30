package com.codeverse.HospitalManagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDTO {

    @NotBlank
    private String userName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
