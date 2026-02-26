package com.security.auth.service.impl;

import com.security.auth.dto.UserDto;
import com.security.auth.entity.Role;
import com.security.auth.entity.User;
import com.security.auth.repository.RoleRepository;
import com.security.auth.repository.UserRepository;
import com.security.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName()+" "+userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role==null){
            role=roleCheckExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    public Role roleCheckExist()
    {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
