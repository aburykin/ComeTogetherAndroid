package ru.bur.cometogetherandroid.activities.showMeeting;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import ru.bur.cometogetherandroid.R;

public class ShowMeeting extends AppCompatActivity {

    Button joinToMeetingBtn;
    Button leaveMeetingBtn;

    TextInputEditText meetingName;
    TextInputEditText meetingPlace;
    TextInputEditText meetingDate;
    TextInputEditText meetingTime;
    TextInputEditText meetingDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_meeting);
        joinToMeetingBtn = findViewById(R.id.joinToMeetingBtn);
        leaveMeetingBtn = findViewById(R.id.leaveMeetingBtn);
        joinToMeetingBtn.setOnClickListener(v->{
            //TODO здесь происходит присоединение человека к встрече, если он уже в ней не состоит. Настроить видимость этой кнопки
        });

        leaveMeetingBtn.setOnClickListener(v->{
            //TODO здесь происходит отключение человека от встречи, если он в ней состоит.Настроить видимость этой кнопки
        });

        // тестовые скрипты
        meetingName = findViewById(R.id.userPhone);
        meetingPlace = findViewById(R.id.meetingPlace);
        meetingDate = findViewById(R.id.meetingDate);
        meetingTime = findViewById(R.id.meetingTime);
        meetingDescription = findViewById(R.id.meetingDescription);

        meetingName.setText("тестовое название встречи");
        meetingPlace.setText("Место встречи изменить нельзя!");
        meetingDate.setText("23.03.2019");
        meetingTime.setText("23:59");
        meetingDescription.setText("тестовое название встречи такое большое что совсем не умещается на одном экране, поэтому оно будет переноситься по нему");



    }
}
