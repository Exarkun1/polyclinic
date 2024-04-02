package com.propcool.polyclinic.services;

import com.propcool.polyclinic.models.Job;
import com.propcool.polyclinic.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для работы с должностями
 * */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JobService {
    /**
     * Выдать все должности
     * */
    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    private final JobRepository jobRepository;
}
