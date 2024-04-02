package com.propcool.polyclinic.validation.validators;

import com.propcool.polyclinic.services.AppointmentService;
import com.propcool.polyclinic.validation.annotations.ExistAppointment;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistAppointmentValidator implements ConstraintValidator<ExistAppointment, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return appointmentService.containsAppointment(aLong);
    }
    private final AppointmentService appointmentService;
}
