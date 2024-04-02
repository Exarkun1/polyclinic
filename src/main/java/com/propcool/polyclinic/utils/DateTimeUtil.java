package com.propcool.polyclinic.utils;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с датами
 * */
@Component
public class DateTimeUtil {
    /**
     * Выдать дни из ближайших двух недель
     * */
    public List<LocalDate> twoWeeks() {
        LocalDate localDate = LocalDate.now();
        List<LocalDate> localDates = new ArrayList<>();
        for(int i = 0; i < 14; i++) {
            localDate = localDate.plusDays(1);
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            if(dayOfWeek != DayOfWeek.SUNDAY && dayOfWeek != DayOfWeek.SATURDAY) {
                localDates.add(localDate);
            }
        }
        return localDates;
    }

    /**
     * Выдать список времени приёма в первую смену
     * */
    public List<LocalTime> shiftFirst() {
        LocalTime localTime = LocalTime.parse("08:00:00");
        List<LocalTime> localTimes = new ArrayList<>();
        for(int i = 0; i < 32; i++) {
            localTimes.add(localTime);
            localTime = localTime.plusMinutes(15);
        }
        return localTimes;
    }

    /**
     * Выдать список времени приёма во вторую смену
     * */
    public List<LocalTime> shiftSecond() {
        LocalTime localTime = LocalTime.parse("12:00:00");
        List<LocalTime> localTimes = new ArrayList<>();
        for(int i = 0; i < 32; i++) {
            localTimes.add(localTime);
            localTime = localTime.plusMinutes(15);
        }
        return localTimes;
    }

    /**
     * Проверить что дата находится в промежутке ближайших 2 недель
     * */
    public boolean inTwoWeeks(LocalDateTime time) {
        LocalDate curDate = LocalDate.now();
        LocalDate date = time.toLocalDate();
        return date.isAfter(curDate) && date.isBefore(curDate.plusDays(14));
    }
}
