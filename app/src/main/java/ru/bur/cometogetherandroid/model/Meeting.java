package ru.bur.cometogetherandroid.model;

import java.time.LocalTime;

import lombok.Data;

@Data
public class Meeting {
    private final String name;
    private final String place;
    private final LocalTime time;
}
