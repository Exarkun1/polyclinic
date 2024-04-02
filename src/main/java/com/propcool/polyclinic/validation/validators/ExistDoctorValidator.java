package com.propcool.polyclinic.validation.validators;

import com.propcool.polyclinic.services.DoctorService;
import com.propcool.polyclinic.validation.annotations.ExistDoctor;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistDoctorValidator implements ConstraintValidator<ExistDoctor, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return doctorService.containsDoctor(aLong);
    }
    private final DoctorService doctorService;
}
