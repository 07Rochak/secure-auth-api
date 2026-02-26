package com.security.auth.controller;

import com.security.auth.dto.UserDto;
import com.security.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AuthController {

    private UserService userService;

    @GetMapping("/index")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model)
    {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@ModelAttribute("user") UserDto userDto){
        userService.saveUser(userDto);
        return "redirect:/register?success";
    }
}
