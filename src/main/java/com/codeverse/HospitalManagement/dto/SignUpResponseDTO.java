package com.codeverse.HospitalManagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponseDTO {

    private Long userId;

    private String userName;

    private String email;
}
