package com.propcool.polyclinic.validation.validators;

import com.propcool.polyclinic.models.Appointment;
import com.propcool.polyclinic.services.AppointmentService;
import com.propcool.polyclinic.validation.annotations.AfterAppointment;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class AfterAppointmentValidator implements ConstraintValidator<AfterAppointment, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        Appointment appointment = appointmentService.getAppointment(aLong);
        return appointment == null || appointment.getDateTime().isBefore(LocalDateTime.now());
    }
    private final AppointmentService appointmentService;
}
