package ru.bur.cometogetherandroid;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MeetingScrollerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_scroller);
        TableLayout tableLayout = findViewById(R.id.meeting_scroller_table);
        for (int i = 0; i < 100; i++) {
            tableLayout.addView(createTableRow());
        }
    }

    private TableRow createTableRow() {
        TableRow tableRow = new TableRow(this);
        tableRow.addView(withText("Волейбол с друзьями"));
        tableRow.addView(withText("Площадка во дворе"));
        tableRow.addView(withText("18:00"));
        return tableRow;
    }

    private TextView withText(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        return textView;
    }
}
