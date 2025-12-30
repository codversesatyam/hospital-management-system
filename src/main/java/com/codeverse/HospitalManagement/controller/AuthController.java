package com.codeverse.HospitalManagement.controller;

import com.codeverse.HospitalManagement.dto.LoginReponseDTO;
import com.codeverse.HospitalManagement.dto.LoginRequestDTO;
import com.codeverse.HospitalManagement.dto.SignUpRequestDTO;
import com.codeverse.HospitalManagement.dto.SignUpResponseDTO;
import com.codeverse.HospitalManagement.entity.User;
import com.codeverse.HospitalManagement.service.AuthService;
import com.codeverse.HospitalManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginReponseDTO> login(@RequestBody LoginRequestDTO dto){

        String token = authService.login(
                dto.getUserName(),
                dto.getPassword()
        );

        User user = userService
                .getUserByUserName(dto.getUserName());

        LoginReponseDTO response = new LoginReponseDTO();
        response.setJwt(token);
        response.setUserId(user.getId());

        return ResponseEntity.ok(response);

    }

    @PostMapping("/signup")
    private ResponseEntity<SignUpResponseDTO> signUp(@RequestBody SignUpRequestDTO requestDTO){

        User user = new User();
        user.setUserName(requestDTO.getUserName());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());

        User savedUser = userService.registerUser(user);

        SignUpResponseDTO responseDTO = new SignUpResponseDTO();

        responseDTO.setUserId(savedUser.getId());
        responseDTO.setUserName(savedUser.getUsername());
        responseDTO.setEmail(savedUser.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }






}
