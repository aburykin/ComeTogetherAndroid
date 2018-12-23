package ru.bur.cometogetherandroid.activities.meetingScroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import ru.bur.cometogetherandroid.R;
import ru.bur.cometogetherandroid.model.Meeting;

public class MeetingScroller extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_scroller);

        listView = findViewById(R.id.meetingList);

        List<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            meetings.add(new Meeting("Волейбол с друзьями", "Площадка во дворе", LocalDate.of(2018, 11, 5), LocalTime.of(18, 0), i));
        }

        MeetingScrollerRowAdapter meetingScrollerRowAdapter = new MeetingScrollerRowAdapter(this, meetings);
        listView.setAdapter(meetingScrollerRowAdapter);

    }

}
