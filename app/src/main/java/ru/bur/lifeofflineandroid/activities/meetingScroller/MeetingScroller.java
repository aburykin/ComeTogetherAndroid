package ru.bur.lifeofflineandroid.activities.meetingScroller;

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
import ru.bur.lifeofflineandroid.LifeOfflineApp;
import ru.bur.lifeofflineandroid.R;
import ru.bur.lifeofflineandroid.activities.authorization.Authorization;
import ru.bur.lifeofflineandroid.common.AppIntents;
import ru.bur.lifeofflineandroid.model.Meeting;

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
        ((LifeOfflineApp) getApplicationContext()).getAppComponent().inject(this);
        presender.attachView(this);

        meetings = new ArrayList<>();
        meetingScrollerRowAdapter = new MeetingScrollerRowAdapter(this, meetings);
        listView.setAdapter(meetingScrollerRowAdapter);

        addMeeting.setOnClickListener(view -> {
            Intent intent = new Intent(AppIntents.MEETING_CREATE);
            startActivity(intent);
        });

        presender.getMeetingsFromDB(meetings);
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
