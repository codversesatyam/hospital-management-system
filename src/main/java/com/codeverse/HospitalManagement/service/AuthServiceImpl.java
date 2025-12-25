package com.codeverse.HospitalManagement.service;

import com.codeverse.HospitalManagement.entity.User;
import com.codeverse.HospitalManagement.repository.UserRepository;
import com.codeverse.HospitalManagement.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final AuthUtil authUtil;

    @Override
    public String login(String username, String password) {

        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( username , password));

        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        return authUtil.generateAccessToken(user);
    }
}
