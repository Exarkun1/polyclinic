package com.propcool.polyclinic.services;

import com.propcool.polyclinic.models.Appointment;
import com.propcool.polyclinic.models.Doctor;
import com.propcool.polyclinic.repositories.DoctorRepository;
import com.propcool.polyclinic.utils.security.UserDetails;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для работы с докторами
 * */
@Service
@Transactional
@RequiredArgsConstructor
public class DoctorService {
    /**
     * Выдать докторов с данной должностью, либо всех
     * */
    @Transactional(readOnly = true)
    public List<Doctor> getDoctors(String jobName) {
        if(jobName == null || jobName.isEmpty()) {
            return doctorRepository.findAll();
        } else {
            return doctorRepository.findWithJobName(jobName);
        }
    }

    /**
     * Выдать доктора
     * */
    @Transactional(readOnly = true)
    public Doctor getDoctor(long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    /**
     * Выдать данные текущего пользователя-доктора
     * */
    @Transactional(readOnly = true)
    public Doctor getDoctor(UserDetails userDetails) {
        return doctorRepository.findWithLogin(userDetails.login()).orElse(null);
    }

    /**
     * Проверить существование доктора
     * */
    @Transactional(readOnly = true)
    public boolean containsDoctor(long id) {
        return getDoctor(id) != null;
    }

    private final DoctorRepository doctorRepository;
}
