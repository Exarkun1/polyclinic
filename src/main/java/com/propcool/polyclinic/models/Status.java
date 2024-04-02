package com.propcool.polyclinic.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Status {
    Expectation("Ожидание"), NoShow("Отсутствие"), Healthy("Здоров"), Ill("Болен");

    private final String ruName;

    public static Status[] valuesWithoutExpectation() {
        return Arrays.stream(values()).filter(status -> status != Expectation).toArray(Status[]::new);
    }
}
