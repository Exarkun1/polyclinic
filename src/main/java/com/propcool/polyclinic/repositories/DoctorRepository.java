package com.propcool.polyclinic.repositories;

import com.propcool.polyclinic.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("select d from Doctor d join d.job j where j.name = :jobName")
    List<Doctor> findWithJobName(String jobName);

    @Query("select d from Doctor d join d.userData u where u.login = :login")
    Optional<Doctor> findWithLogin(String login);
}
