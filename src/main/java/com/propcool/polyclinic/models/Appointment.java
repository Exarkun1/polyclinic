package com.propcool.polyclinic.models;

import com.propcool.polyclinic.dto.EditAppointmentRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "appointment")
@Getter @Setter @NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_gen")
    @SequenceGenerator(name = "appointment_gen", sequenceName = "appointment_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "patient_comment")
    private String patientComment;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "treatment_measures")
    private String treatmentMeasures;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    public EditAppointmentRequest getEditAppointmentRequest() {
        EditAppointmentRequest appointmentRequest = new EditAppointmentRequest();
        appointmentRequest.setDiagnosis(diagnosis);
        appointmentRequest.setTreatmentMeasures(treatmentMeasures);
        appointmentRequest.setStatus((status == Status.Expectation ? null : status));
        return appointmentRequest;
    }
}
