package com.propcool.polyclinic.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    Patient("Пациент"), Doctor("Доктор");
    private final String ruName;
}
