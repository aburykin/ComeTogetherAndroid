package ru.bur.cometogetherandroid.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class Meeting {
    private final String name;
    private final String place;
    private final LocalDate date;
    private final LocalTime time;
    private final Integer amountParticipants;
}
