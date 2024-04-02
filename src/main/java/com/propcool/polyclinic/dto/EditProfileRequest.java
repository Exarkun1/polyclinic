package com.propcool.polyclinic.dto;

import com.propcool.polyclinic.validation.annotations.UniqueLogin;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Запрос на изменения профиля пациентом
 * */
@Getter @Setter @NoArgsConstructor
public class EditProfileRequest {
    @NotEmpty(message = "Логин не должен быть пустым")
    @Size(min = 4, max = 200, message = "Размер логина не должен быть меньше 4 и больше 200 символов")
    @UniqueLogin(message = "Данный логин уже занят")
    public String login;

    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 4, max = 50, message = "Размер ФИО не должен быть меньше 4 и больше 200 символов")
    @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+( [А-Я][а-я]+)?", message = "Неверный паттерн ФИО")
    private String fullName;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent(message = "Дата вашего рождения не может быть в будущем")
    @NotNull(message = "Дата рождения не должна быть пустой")
    private LocalDate birthday;

    @NotEmpty(message = "Адрес не должен быть пустым")
    @Size(min = 4, max = 200, message = "Размер адреса не должен быть меньше 4 и больше 200 символов")
    private String address;

    private String oldPassword;
}
