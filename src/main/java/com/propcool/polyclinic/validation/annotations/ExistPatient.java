package com.propcool.polyclinic.validation.annotations;

import com.propcool.polyclinic.validation.validators.ExistPatientValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Проверка существования пациента
 * */
@Constraint(validatedBy = ExistPatientValidator.class) // Класс для проверки
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistPatient {
    String message() default "Error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
