package com.propcool.polyclinic.validation.annotations;

import com.propcool.polyclinic.validation.validators.ConfirmPasswordValidator;
import com.propcool.polyclinic.validation.validators.EditConfirmPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Проверка подтверждения пароля
 * */
@Constraint(validatedBy = {
        ConfirmPasswordValidator.class,
        EditConfirmPasswordValidator.class
}) // Классы для проверки
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPassword {
    String message() default "Error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
