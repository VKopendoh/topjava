package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends TemporalAccessor> boolean isBetween(T lt, T startTime, T endTime) {
        boolean result = false;
        if (lt.getClass() == LocalDate.class) {
            result = LocalDate.from(lt).compareTo(LocalDate.from(startTime)) >= 0
                    && LocalDate.from(lt).compareTo(LocalDate.from(endTime)) <= 0;
        } else if (lt.getClass() == LocalTime.class) {
            result = LocalTime.from(lt).compareTo(LocalTime.from(startTime)) >= 0
                    && LocalTime.from(lt).compareTo(LocalTime.from(endTime)) <= 0;
        }
        return result;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

