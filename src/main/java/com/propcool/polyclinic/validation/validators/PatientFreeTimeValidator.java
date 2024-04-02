package com.propcool.polyclinic.validation.validators;

import com.propcool.polyclinic.dto.AppointmentRequest;
import com.propcool.polyclinic.models.Appointment;
import com.propcool.polyclinic.services.AppointmentService;
import com.propcool.polyclinic.utils.security.SecurityUtil;
import com.propcool.polyclinic.validation.annotations.PatientFreeTime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class PatientFreeTimeValidator implements ConstraintValidator<PatientFreeTime, AppointmentRequest> {
    @Override
    public boolean isValid(AppointmentRequest appointmentRequest, ConstraintValidatorContext constraintValidatorContext) {
        List<LocalDateTime> dateTimes = appointmentService.getPatientAppointments(securityUtil.getDetails())
                .stream().map(Appointment::getDateTime).toList();
        LocalDateTime dateTime = appointmentRequest.getDate().atTime(appointmentRequest.getTime());
        return !dateTimes.contains(dateTime);
    }
    private final AppointmentService appointmentService;
    private final SecurityUtil securityUtil;
}
