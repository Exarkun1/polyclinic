package com.propcool.polyclinic.validation.annotations;

import com.propcool.polyclinic.validation.validators.OldPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Проверка совпадения старого пароля
 * */
@Constraint(validatedBy = OldPasswordValidator.class) // Класс для проверки
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OldPassword {
    String message() default "Error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
