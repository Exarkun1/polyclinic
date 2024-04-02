package com.propcool.polyclinic.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "doctor")
@Getter @Setter @NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_gen")
    @SequenceGenerator(name = "doctor_gen", sequenceName = "doctor_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "shift")
    private int shift;

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

    @OneToOne
    @JoinColumn(name = "user_data_id", referencedColumnName = "id")
    private UserData userData;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    public String getShiftName() {
        return shift % 2 == 0 ? "Чётная" : "Нечётная";
    }

    public void addAppointment(Appointment appointment) {
        appointment.setDoctor(this);
        appointments.add(appointment);
    }
}
