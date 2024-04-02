package com.propcool.polyclinic.converters;

import com.propcool.polyclinic.dto.AppointmentRequest;
import com.propcool.polyclinic.models.Appointment;
import com.propcool.polyclinic.models.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class AppointmentRequestToAppointmentConverter implements Converter<AppointmentRequest, Appointment> {
    @Override
    public Appointment convert(AppointmentRequest source) {
        LocalDateTime dateTime = source.getDate().atTime(source.getTime());
        Appointment appointment = new Appointment();
        appointment.setDateTime(dateTime);
        appointment.setStatus(Status.Expectation);
        appointment.setPatientComment(source.getComment());
        return appointment;
    }
}
