package ru.bur.cometogetherandroid.activities.createMeeting;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.time.LocalDate;
import java.time.LocalTime;

import ru.bur.cometogetherandroid.R;
import ru.bur.cometogetherandroid.activities.meetingScroller.MeetingScroller;

public class CreateMeeting extends AppCompatActivity {

    private final int DIALOG_DATE = 1;
    private final int DIALOG_TIME = 2;

    TextInputEditText meetingDate;
    TextInputEditText meetingTime;
    Button createNewMeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_meeting);
        meetingDate = findViewById(R.id.meetingDate);
        meetingTime = findViewById(R.id.meetingTime);
        createNewMeeting = findViewById(R.id.createNewMeeting);
        meetingDate.setOnClickListener(v -> showDialog(DIALOG_DATE));
        meetingTime.setOnClickListener(v -> showDialog(DIALOG_TIME));
        createNewMeeting.setOnClickListener(v->{

            //TODO отправляет запрос в БД

            Intent intent = new Intent(this, MeetingScroller.class);
            startActivity(intent);
        });


    }


    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            LocalDate localDate = LocalDate.now();
            DatePickerDialog dateDialog = new DatePickerDialog(this, dateCallBackListener, localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
            return dateDialog;
        } else if (id == DIALOG_TIME) {
            LocalTime localTime = LocalTime.now();
            TimePickerDialog timeDialog = new TimePickerDialog(this, timeCallBackListener, localTime.getHour(), localTime.getMinute(), true);
            return timeDialog;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener dateCallBackListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            meetingDate.setText(String.format("%d.%d.%d", dayOfMonth, monthOfYear, year));
        }
    };

    TimePickerDialog.OnTimeSetListener timeCallBackListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            meetingTime.setText(String.format("%d:%d", hourOfDay, minute));
        }
    };


}
