package ru.bur.cometogetherandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

import ru.bur.cometogetherandroid.adapter.ParticipantAdapter;
import ru.bur.cometogetherandroid.model.Participant;

public class Participants extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participants);
        gridView = findViewById(R.id.participantsOfMeeting);


        ArrayList<Participant> participantsTest = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            participantsTest.add(new Participant(String.valueOf(i),R.drawable.ic_launcher_foreground));
        }
        ParticipantAdapter participantAdapter = new ParticipantAdapter(this, participantsTest);
        gridView.setAdapter(participantAdapter);

    }


}
