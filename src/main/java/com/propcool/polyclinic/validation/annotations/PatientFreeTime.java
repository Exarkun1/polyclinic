package com.propcool.polyclinic.validation.annotations;

import com.propcool.polyclinic.validation.validators.PatientFreeTimeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Проверка отсутствия записи пациента к доктору в данное время
 * */
@Constraint(validatedBy = PatientFreeTimeValidator.class) // Класс для проверки
@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PatientFreeTime {
    String message() default "Error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
