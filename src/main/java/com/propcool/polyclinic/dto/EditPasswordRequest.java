package com.propcool.polyclinic.dto;

import com.propcool.polyclinic.validation.annotations.ConfirmPassword;
import com.propcool.polyclinic.validation.annotations.OldPassword;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Запрос на изменение пароля пациентом
 * */
@ConfirmPassword(message = "Пароли не совпали")
@Getter @Setter @NoArgsConstructor
public class EditPasswordRequest {
    @OldPassword(message = "Старый пароль не совпал")
    private String oldPassword;

    @NotEmpty(message = "Новый пароль не должен быть пустым")
    @Size(min = 8, max = 50, message = "Размер нового пароля не должен быть меньше 8 и больше 50 символов")
    private String newPassword;

    private String confirmPassword;
}
