package com.codeverse.HospitalManagement.service;

import com.codeverse.HospitalManagement.Enum.Role;
import com.codeverse.HospitalManagement.Exception.UserNotFoundException;
import com.codeverse.HospitalManagement.entity.User;
import com.codeverse.HospitalManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {

        if(userRepository.existsByUserName(user.getUsername())){
            throw new RuntimeException("Username already Exists!");
        }

        if(userRepository.existsByEmail(user.getEmail())){

            throw new RuntimeException("Email Already Exists");

        }
        //Encrypt the Code
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole(Role.PATIENT);

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not found"));
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
    }


}
