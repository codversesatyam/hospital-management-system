package com.codeverse.HospitalManagement.service;

import com.codeverse.HospitalManagement.entity.User;

public interface UserService {

    User registerUser(User user);

    User getUserById(Long id);

    User getUserByUserName(String userName);


}
