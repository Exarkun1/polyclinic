package com.propcool.polyclinic.repositories;

import com.propcool.polyclinic.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("select p from Patient p join p.userData u where u.login = :login")
    Optional<Patient> findWithLogin(String login);

    List<Patient> findByFullNameStartingWith(String name);
}
