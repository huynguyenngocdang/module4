package com.codegym.clubv3.service;

import com.codegym.clubv3.dto.RegistrationDto;
import com.codegym.clubv3.entity.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
