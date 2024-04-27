package com.propcool.polyclinic.repositories;

import com.propcool.polyclinic.models.Appointment;
import com.propcool.polyclinic.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("select a from Appointment a join a.patient p join p.userData u where u.login = :login order by a.dateTime desc")
    List<Appointment> findWithPatientLogin(String login);

    @Query("select a from Appointment a join a.doctor d join d.userData u where u.login = :login and a.status = :status order by a.dateTime")
    List<Appointment> findWithDoctorLoginAndStatus(String login, Status status);

    @Query("select a from Appointment a join a.doctor d join d.userData u join a.patient p where u.login = :login and a.status != :status and p.id = :id order by a.dateTime")
    List<Appointment> findWithPatientIdAndDoctorLoginAndNoStatus(long id, String login, Status status);
}
