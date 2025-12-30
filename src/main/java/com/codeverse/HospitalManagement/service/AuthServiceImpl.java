package com.codeverse.HospitalManagement.service;

import com.codeverse.HospitalManagement.entity.User;
import com.codeverse.HospitalManagement.repository.UserRepository;
import com.codeverse.HospitalManagement.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthUtil authUtil;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Override
    public String login(String username, String password) {

        AuthenticationManager authenticationManager;
        try {
            authenticationManager = authenticationConfiguration.getAuthenticationManager();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        return authUtil.generateAccessToken(user);
    }
}

