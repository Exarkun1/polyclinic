package com.propcool.polyclinic.services;

import com.propcool.polyclinic.dto.AppointmentRequest;
import com.propcool.polyclinic.dto.EditAppointmentRequest;
import com.propcool.polyclinic.models.Appointment;
import com.propcool.polyclinic.models.Doctor;
import com.propcool.polyclinic.models.Patient;
import com.propcool.polyclinic.models.Status;
import com.propcool.polyclinic.repositories.AppointmentRepository;
import com.propcool.polyclinic.utils.security.SecurityUtil;
import com.propcool.polyclinic.utils.security.UserDetails;
import com.propcool.polyclinic.utils.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сервис для работы с записями
 * */
@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentService {
    /**
     * Добавить запись
     * */
    public void addAppointment(long id, AppointmentRequest appointmentRequest, UserDetails userDetails) {
        Patient patient = patientService.getPatient(userDetails.login());
        Doctor doctor = doctorService.getDoctor(id);
        Appointment appointment = Objects.requireNonNull(conversionService.convert(appointmentRequest, Appointment.class));
        patient.addAppointment(appointment);
        doctor.addAppointment(appointment);
        appointmentRepository.save(appointment);
    }

    /**
     * Выдать записи текущего пользователя-пациента
     * */
    @Transactional(readOnly = true)
    public List<Appointment> getPatientAppointments(UserDetails userDetails) {
        return appointmentRepository.findWithPatientLogin(userDetails.login());
    }

    /**
     * Выдать записи пациента к текущему пользователю-доктору
     * */
    @Transactional(readOnly = true)
    public List<Appointment> getPatientHistoryWithDoctor(long patientId, UserDetails doctorUserDetails) {
        return appointmentRepository.findWithPatientIdAndDoctorLoginAndNoStatus(patientId, doctorUserDetails.login(), Status.Expectation);
    }

    /**
     * Выдать запись
     * */
    @Transactional(readOnly = true)
    public Appointment getAppointment(long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    /**
     * Проверить существование записи
     * */
    @Transactional(readOnly = true)
    public boolean containsAppointment(long id) {
        return getAppointment(id) != null;
    }

    /**
     * Выдать список свободного времени доктора на ближайшие 2 недели
     * */
    @Transactional(readOnly = true)
    public List<Pair<LocalDate, List<LocalTime>>> getTwoWeeksFreeTime(long id) {
        // Получаем доктора
        Doctor doctor = doctorService.getDoctor(id);
        // Получаем список часов, в которые доктор занят в ближайшие 2 недели
        List<LocalDateTime> dateTimes = doctor.getAppointments().stream()
                .map(Appointment::getDateTime)
                .filter(dateTimeUtil::inTwoWeeks).toList();
        // Получаем рабочие дни ближайших 2 недель
        List<LocalDate> dates = dateTimeUtil.twoWeeks();
        List<Pair<LocalDate, List<LocalTime>>> pairs = new ArrayList<>();
        // Для каждой даты в плучаем список часов
        for(var date : dates) {
            // Получаем часы, в которые доктор занят в данный день
            List<LocalTime> times = dateTimes.stream()
                    .filter(dt -> dt.toLocalDate().isEqual(date))
                    .map(LocalDateTime::toLocalTime).toList();
            // В зависимости от сменности доктора выбираем, список часов на данный день
            if(date.getDayOfMonth() % 2 == doctor.getShift()) {
                // Получаем список часов для нечётной смены
                List<LocalTime> first = dateTimeUtil.shiftFirst();
                // Удаляем из списка часы, в которые доктор уже занят в данный день
                first.removeAll(times);
                pairs.add(new Pair<>(date, first));
            } else {
                // Получаем список часов для чётной смены
                List<LocalTime> second = dateTimeUtil.shiftSecond();
                // Удаляем из списка часы, в которые доктор уже занят в данный день
                second.removeAll(times);
                pairs.add(new Pair<>(date, second));
            }
        }
        return pairs;
    }

    /**
     * Выдать записи в статусе ожидания текущего пользователя-доктора
     * */
    public List<Appointment> getDoctorExpAppointments(UserDetails details) {
        return appointmentRepository.findWithDoctorLoginAndStatus(details.login(), Status.Expectation);
    }

    /**
     * Изменить результаты приёма
     * */
    public void editAppointment(Long id, EditAppointmentRequest appointmentRequest) {
        Appointment appointment = getAppointment(id);
        appointment.setStatus(appointmentRequest.getStatus());
        appointment.setDiagnosis(appointmentRequest.getDiagnosis());
        appointment.setTreatmentMeasures(appointmentRequest.getTreatmentMeasures());
    }

    /**
     * Удалить запись
     * */
    public void deleteAppointment(long id) {
        appointmentRepository.deleteById(id);
    }

    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final DateTimeUtil dateTimeUtil;
    private final ConversionService conversionService;
}
