package com.propcool.polyclinic.validation.annotations;

import com.propcool.polyclinic.validation.validators.CorrectAuthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Проверка правильности авторизации
 * */
@Constraint(validatedBy = CorrectAuthValidator.class) // Класс для проверки
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CorrectAuth {
    String message() default "Error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
