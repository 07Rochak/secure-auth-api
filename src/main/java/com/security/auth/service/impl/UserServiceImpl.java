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
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users= userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map((user)->mapUserToUserDto(user))
                .collect(Collectors.toList());
        return userDtos;
    }

    public Role roleCheckExist()
    {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    public UserDto mapUserToUserDto(User user){
        String[] name = user.getName().split(" ");
        String firstName = name[0];
        String lastName = name[1];
        UserDto userDto = new UserDto(user.getId(),
                firstName,
                lastName,
                user.getEmail(),
                user.getPassword());
        return userDto;
    }
}
