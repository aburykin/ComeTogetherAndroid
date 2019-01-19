package ru.bur.cometogetherandroid.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class Meeting{
    private Long id;
    private String name;
    private String place;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private Integer amountParticipants;
}
