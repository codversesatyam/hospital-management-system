package com.codeverse.HospitalManagement.controller;

import com.codeverse.HospitalManagement.dto.LoginReponseDTO;
import com.codeverse.HospitalManagement.dto.LoginRequestDTO;
import com.codeverse.HospitalManagement.service.AuthService;
import com.codeverse.HospitalManagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


}
