package com.propcool.polyclinic.validation.validators;

import com.propcool.polyclinic.models.Appointment;
import com.propcool.polyclinic.models.Status;
import com.propcool.polyclinic.services.AppointmentService;
import com.propcool.polyclinic.validation.annotations.StatusIs;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StatusValidator implements ConstraintValidator<StatusIs, Long> {
    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        Appointment appointment = appointmentService.getAppointment(aLong);
        return appointment == null || appointment.getStatus() == status;
    }
    @Override
    public void initialize(StatusIs constraintAnnotation) {
        status = constraintAnnotation.value();
    }
    private Status status;
    private final AppointmentService appointmentService;
}
