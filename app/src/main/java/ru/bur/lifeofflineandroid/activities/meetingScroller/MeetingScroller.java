package ru.bur.lifeofflineandroid.activities.meetingScroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
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
import ru.bur.lifeofflineandroid.model.MapperMeetingDto;
import ru.bur.lifeofflineandroid.model.Meeting;

public class MeetingScroller extends AppCompatActivity {

    @BindView(R.id.meetingList)
    ListView listView;

    @BindView(R.id.addMeeting)
    FloatingActionButton addMeeting;

    @Inject
    public MeetingScrollerPresender presender;

    private List<Meeting> meetingsList;

    MeetingScrollerRowAdapter meetingScrollerRowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_scroller);
        ButterKnife.bind(this);
        ((LifeOfflineApp) getApplicationContext()).getAppComponent().inject(this);
        presender.attachView(this);

        meetingsList = new ArrayList<>();
        meetingScrollerRowAdapter = new MeetingScrollerRowAdapter(this, meetingsList);
        listView.setAdapter(meetingScrollerRowAdapter);

        addMeeting.setOnClickListener(view -> {
            Intent intent = new Intent(AppIntents.MEETING_CREATE);
            startActivity(intent);
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                System.out.println();
                if (scrollState == SCROLL_STATE_IDLE
                        && listView.getLastVisiblePosition() == meetingsList.size() - 1) {
                    //  progressBar.setVisibility(View.VISIBLE);
                    Meeting meeting = (Meeting)meetingScrollerRowAdapter.getItem(listView.getLastVisiblePosition());
                    presender.getNextMeetings(meetingsList, MapperMeetingDto.toDto(meeting));
                    //   progressBar.setVisibility(View.GONE);
                }

                else if (scrollState == SCROLL_STATE_IDLE && listView.getFirstVisiblePosition() == 0) { // пролистываем вверх
                    //  progressBar.setVisibility(View.VISIBLE);
                    presender.getFirstNmeatings(meetingsList);
                    //   progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //we don't need this method, so we leave it empty
            }
        });

        presender.getFirstNmeatings(meetingsList);

    }

    public void updateMeetingScroller() {
        meetingScrollerRowAdapter.notifyDataSetChanged();
    }

    public void goToAuthorization() {
        Intent intent = new Intent(this, Authorization.class);
        startActivity(intent);
    }
}
