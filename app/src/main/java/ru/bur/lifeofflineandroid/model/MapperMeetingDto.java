package ru.bur.lifeofflineandroid.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import ru.bur.dto.MeetingDto;

public class MapperMeetingDto {

    public static MeetingDto toDto(Meeting meeting) {
        MeetingDto dto = new MeetingDto();
        dto.setMeetingId(meeting.getMeetingId());
        dto.setName(meeting.getName());
        dto.setPlace(meeting.getPlace());
        dto.setStartDateTime(getStartDateTime(meeting));
        dto.setDescription(meeting.getDescription());
        return dto;
    }

    public static Meeting toModel(MeetingDto dto) {
        Meeting meeting = new Meeting();
        meeting.setMeetingId(dto.getMeetingId());
        meeting.setName(dto.getName());
        meeting.setPlace(dto.getPlace());
        if (dto.getStartDateTime() != 0) {
            LocalDateTime startDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dto.getStartDateTime()), ZoneId.systemDefault());
            // теперь преобразую его во время пользователя
            ZonedDateTime zonedDateTime = startDate.atZone(ZoneId.of("UTC"));
            startDate = LocalDateTime.ofInstant(zonedDateTime.toInstant(), ZoneId.systemDefault());

            meeting.setDate(startDate.toLocalDate());
            meeting.setTime(startDate.toLocalTime());
        }
        meeting.setDescription(dto.getDescription());
        return meeting;
    }

    /**
     * Получаем дату и время встречи в UTC.  При этом время преобразуемся к моменту в UTC.
     * Например: встреча в москве(+3:00) в 18.00 преобразуется во встречу в UTC как 15.00
     *
     * @return
     */
    private static long getStartDateTime(Meeting meeting) {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("d.M.y");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("H:m");

        StringBuilder sb = new StringBuilder()
                .append(meeting.getDate().format(formatterDate))
                .append(" ")
                .append(meeting.getTime().format(formatterTime));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.y H:m");
        ZonedDateTime zonedDateTime = LocalDateTime.parse(sb.toString(), formatter).atZone(ZoneId.systemDefault());
        LocalDateTime utcDateTime = LocalDateTime.ofInstant(zonedDateTime.toInstant(), ZoneId.of("UTC"));
        return utcDateTime.toInstant(ZoneOffset.of(ZonedDateTime.now(ZoneId.systemDefault()).getOffset().getId())).toEpochMilli();
    }
}
