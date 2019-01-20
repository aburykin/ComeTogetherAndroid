package ru.bur.cometogetherandroid.activities.meetingScroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.bur.cometogetherandroid.ComeTogetherApp;
import ru.bur.cometogetherandroid.R;
import ru.bur.cometogetherandroid.activities.authorization.Authorization;
import ru.bur.cometogetherandroid.activities.createMeeting.CreateMeeting;
import ru.bur.cometogetherandroid.model.Meeting;

public class MeetingScroller extends AppCompatActivity {

    @BindView(R.id.meetingList)
    ListView listView;

    @BindView(R.id.addMeeting)
    FloatingActionButton addMeeting;

    @Inject
    public MeetingScrollerPresender presender;

    private List<Meeting> meetings;

    MeetingScrollerRowAdapter meetingScrollerRowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_scroller);
        ButterKnife.bind(this);
        ((ComeTogetherApp) getApplicationContext()).getAppComponent().inject(this);
        presender.attachView(this);

        meetings = new ArrayList<>();
        meetingScrollerRowAdapter = new MeetingScrollerRowAdapter(this, meetings);
        listView.setAdapter(meetingScrollerRowAdapter);

        addMeeting.setOnClickListener(view -> {
            // Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            Intent intent = new Intent(view.getContext(), CreateMeeting.class);
            startActivity(intent);
        });

        presender.getMeetings(meetings);

    }

    public void updateMeetingScroller() {
        meetingScrollerRowAdapter.notifyDataSetChanged();
    }

    public void goToAuthorization() {
        Intent intent = new Intent(this, Authorization.class);
        startActivity(intent);
    }
}
