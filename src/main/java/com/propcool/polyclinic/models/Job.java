package com.propcool.polyclinic.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "job")
@Getter @Setter @NoArgsConstructor
public class Job {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "job")
    private List<Doctor> doctors;
}
