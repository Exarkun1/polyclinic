package com.propcool.polyclinic.dto;

import com.propcool.polyclinic.validation.annotations.PatientFreeTime;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Запрос насоздание записи на приём от пациента
 * */
@Getter @Setter @NoArgsConstructor
@PatientFreeTime(message = "У вас уже есть запись на приём в это время")
public class AppointmentRequest {
    private long doctorId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future(message = "Дата записи не может быть в прошлом")
    @NotNull(message = "Дата приёма не может быть пустой")
    private LocalDate date;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Время приёма не может быть пустым")
    private LocalTime time;

    private String comment;

    public AppointmentRequest(long doctorId) {
        this.doctorId = doctorId;
    }
}
