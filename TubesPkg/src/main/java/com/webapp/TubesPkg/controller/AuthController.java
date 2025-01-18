package com.webapp.TubesPkg.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.webapp.TubesPkg.models.AppUser;
import com.webapp.TubesPkg.models.RegisterDto;
import com.webapp.TubesPkg.repository.AppUserRepository;


import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Date;  

@Controller  
public class AuthController {


    @Autowired
    private AppUserRepository repo;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "/register";
    }

    @PostMapping("/register")
    public String register(
            Model model,
            @Valid @ModelAttribute RegisterDto registerDto,
            BindingResult result
    ) {
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            result.addError(
                    new FieldError("registerDto", "confirmPassword",
                            "Password dan Confirm Password tidak sama")
            );
        }
    
        AppUser appUser = repo.findByEmail(registerDto.getEmail());
        if (appUser != null) {
            result.addError(
                    new FieldError("registerDto", "email", "Email sudah digunakan")
            );
        }
    
        if (result.hasErrors()) {
            return "/register";
        }
    
        try {
            BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
    
            AppUser newUser = new AppUser();
            newUser.setFirstName(registerDto.getFirstName());
            newUser.setLastName(registerDto.getLastName());
            newUser.setEmail(registerDto.getEmail());
            newUser.setPhone(registerDto.getPhone());
            newUser.setAddress(registerDto.getAddress());
    
            if (registerDto.getEmail().endsWith("@admin.com")) {
                newUser.setRole("ADMIN");
            } else {
                newUser.setRole("CLIENT");
            }
    
            newUser.setCreatedAt(new Date());
            newUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));
    
            repo.save(newUser);
    
            return "redirect:/UtamaLogin?registered=true";
        } catch (Exception ex) {
            result.addError(
                    new FieldError("registerDto", "firstName", ex.getMessage())
            );
            return "/register";
        }
    }
}