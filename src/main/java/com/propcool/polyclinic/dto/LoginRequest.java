package com.propcool.polyclinic.dto;

import com.propcool.polyclinic.models.Role;
import com.propcool.polyclinic.validation.annotations.CorrectAuth;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Запрос на авторизацию
 * */
@CorrectAuth(message = "Неверный логин или пароль")
@Getter @Setter @NoArgsConstructor
public class LoginRequest {
    private String login;

    private String password;

    private Role role;
}
