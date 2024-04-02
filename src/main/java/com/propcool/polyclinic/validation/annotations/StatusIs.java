package com.propcool.polyclinic.validation.annotations;

import com.propcool.polyclinic.models.Status;
import com.propcool.polyclinic.validation.validators.StatusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Проверка статуса записи на приём
 * */
@Constraint(validatedBy = StatusValidator.class) // Класс для проверки
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusIs {
    String message() default "Error";
    Status value();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
