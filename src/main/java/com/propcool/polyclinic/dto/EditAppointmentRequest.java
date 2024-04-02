package com.propcool.polyclinic.dto;

import com.propcool.polyclinic.models.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Запрос на сохранение результов приёма от доктора
 * */
@Getter @Setter @NoArgsConstructor
public class EditAppointmentRequest {
    @NotNull(message = "Статус приёма не должен быть пустым")
    private Status status;

    @Size(max = 1000, message = "Диагноз не должен быть больше 1000 символов")
    private String diagnosis;

    @Size(max = 1000, message = "Лечебные рекомендации не должены быть больше 1000 символов")
    private String treatmentMeasures;
}
