package com.security.auth.service;

import com.security.auth.dto.UserDto;
import com.security.auth.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
