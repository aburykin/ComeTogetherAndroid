package ru.bur.cometogetherandroid;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.time.LocalTime;

import ru.bur.cometogetherandroid.model.Meeting;

public class MeetingScroller extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_scroller);
        TableLayout tableLayout = findViewById(R.id.meeting_scroller_table);
        for (int i = 0; i < 100; i++) {
            Meeting meeting = new Meeting(
                    "Волейбол с друзьями",
                    "Площадка во дворе",
                    LocalTime.of(18, 0)
            );
            tableLayout.addView(createTableRow(meeting));
        }
    }

    private TableRow createTableRow(Meeting meeting) {
        TableRow tableRow = new TableRow(this);
        tableRow.addView(withText(meeting.getName()));
        tableRow.addView(withText(meeting.getPlace()));
        tableRow.addView(withText(meeting.getTime().toString()));
        return tableRow;
    }

    private TextView withText(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        return textView;
    }
}
