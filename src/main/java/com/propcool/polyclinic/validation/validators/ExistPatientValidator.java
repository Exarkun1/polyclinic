package com.propcool.polyclinic.validation.validators;

import com.propcool.polyclinic.services.PatientService;
import com.propcool.polyclinic.validation.annotations.ExistPatient;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistPatientValidator implements ConstraintValidator<ExistPatient, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return patientService.containsPatient(aLong);
    }
    private final PatientService patientService;
}
