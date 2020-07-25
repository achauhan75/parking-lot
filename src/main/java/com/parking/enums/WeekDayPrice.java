package com.parking.enums;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

public enum WeekDayPrice {

    WEEK_DAY(Arrays.asList(DayOfWeek.of(1), DayOfWeek.of(2), DayOfWeek.of(3), DayOfWeek.of(4), DayOfWeek.of(5))),
    WEEKEND(Arrays.asList(DayOfWeek.of(6), DayOfWeek.of(7)));

    private final List<DayOfWeek> days;

    WeekDayPrice(List<DayOfWeek> days) {
        this.days = days;
    }

    public List<DayOfWeek> getDays() {
        return days;
    }
}
