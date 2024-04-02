package com.propcool.polyclinic.controllers;

import com.propcool.polyclinic.dto.LoginRequest;
import com.propcool.polyclinic.dto.RegisterRequest;
import com.propcool.polyclinic.models.Role;
import com.propcool.polyclinic.utils.security.SecurityUtil;
import com.propcool.polyclinic.services.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер авторизации и регистрации
 * */
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        model.addAttribute("roles", Role.values());
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute @Valid LoginRequest loginRequest, BindingResult errors, Model model) {
        if(errors.hasErrors()) { // В случае неверных данных вновь загружать страницу
            model.addAttribute("roles", Role.values());
            return "auth/login";
        }
        securityUtil.putDetails(loginRequest);
        return "redirect:/" + loginRequest.getRole().name().toLowerCase() + "/main";
    }

    @PostMapping("/logout")
    public String logout() {
        securityUtil.removeDetails();
        return "redirect:/auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid RegisterRequest registerRequest, BindingResult errors) {
        if(errors.hasErrors()) { // В случае неверных данных вновь загружать страницу
            return "auth/register";
        }
        patientService.addPatient(registerRequest);
        return "redirect:/auth/login";
    }

    private final PatientService patientService;
    private final SecurityUtil securityUtil;
}
