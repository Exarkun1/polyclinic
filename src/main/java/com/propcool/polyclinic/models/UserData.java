package com.propcool.polyclinic.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_data")
@Getter @Setter @NoArgsConstructor
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_data_gen")
    @SequenceGenerator(name = "user_data_gen", sequenceName = "user_data_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public UserData(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
