package com.propcool.polyclinic.services;

import com.propcool.polyclinic.dto.EditProfileRequest;
import com.propcool.polyclinic.dto.RegisterRequest;
import com.propcool.polyclinic.models.Patient;
import com.propcool.polyclinic.models.Role;
import com.propcool.polyclinic.repositories.PatientRepository;
import com.propcool.polyclinic.utils.security.SecurityUtil;
import com.propcool.polyclinic.utils.security.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Сервис для работы с пациентами
 * */
@Service
@Transactional
@RequiredArgsConstructor
public class PatientService {
    /**
     * Выдать пациента по его логину
     * */
    @Transactional(readOnly = true)
    public Patient getPatient(String login) {
        return patientRepository.findWithLogin(login).orElse(null);
    }

    /**
     * Выдать пациента
     * */
    @Transactional(readOnly = true)
    public Patient getPatient(long id) {
        return patientRepository.findById(id).orElse(null);
    }

    /**
     * Выдать данные текущего пользователя-пациента
     * */
    @Transactional(readOnly = true)
    public Patient getPatient(UserDetails userDetails) {
        return getPatient(userDetails.login());
    }

    /**
     * Проверить существование пауиента
     * */
    @Transactional(readOnly = true)
    public boolean containsPatient(long id) {
        return getPatient(id) != null;
    }

    /**
     * Выдать пациентов по имени, либо всех
     * */
    public List<Patient> getPatients(String name) {
        if(name == null || name.isEmpty()) {
            return patientRepository.findAll();
        } else {
            return patientRepository.findByFullNameStartingWith(name);
        }
    }

    /**
     * Выдать запрос на изменение данных профиля пациента
     * */
    @Transactional(readOnly = true)
    public EditProfileRequest getEditProfileRequest(UserDetails userDetails) {
        Patient patient = getPatient(userDetails);
        return conversionService.convert(patient, EditProfileRequest.class);
    }

    /**
     * Добавить пациента
     * */
    public void addPatient(RegisterRequest registerRequest) {
        Patient patient = Objects.requireNonNull(conversionService.convert(registerRequest, Patient.class));
        patientRepository.save(patient);
    }

    /**
     * Изменить данные профиля пациента
     * */
    public void editPatient(EditProfileRequest editRequest, UserDetails details) {
        Patient patient = getPatient(details.login());
        patient.getUserData().setLogin(editRequest.getLogin());
        patient.setFullName(editRequest.getFullName());
        patient.setBirthday(editRequest.getBirthday());
        patient.setAddress(editRequest.getAddress());
        securityUtil.putDetails(patient.getUserData().getLogin(), Role.Patient);
    }

    private final PatientRepository patientRepository;
    private final ConversionService conversionService;
    private final SecurityUtil securityUtil;
}
