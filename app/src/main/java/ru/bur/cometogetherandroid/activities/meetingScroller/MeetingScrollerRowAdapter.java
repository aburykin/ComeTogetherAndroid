package ru.bur.cometogetherandroid.activities.meetingScroller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.bur.cometogetherandroid.R;
import ru.bur.cometogetherandroid.activities.createMeeting.CreateMeeting;
import ru.bur.cometogetherandroid.common.AppIntents;
import ru.bur.cometogetherandroid.model.Meeting;


public class MeetingScrollerRowAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Meeting> meetings;

    public MeetingScrollerRowAdapter(Context context, List<Meeting> meetings) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.meetings = meetings;
    }

    @Override
    public int getCount() {
        return meetings.size();
    }

    @Override
    public Object getItem(int position) {
        return meetings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.meeting_scroller_item, parent, false);
        }
        Meeting meeting = getMeeting(position);
        TextView meetingId = convertView.findViewById(R.id.meetingId);
        TextView meetingName = convertView.findViewById(R.id.meetingName);
        TextView meetingPlace = convertView.findViewById(R.id.meetingPlace);
        TextView meetingDate = convertView.findViewById(R.id.meetingDate);
        TextView meetingTime = convertView.findViewById(R.id.meetingTime);
        TextView amountParticipants = convertView.findViewById(R.id.amountParticipants);

        meetingId.setText(String.valueOf(meeting.getMeetingId()));
        meetingName.setText(meeting.getName());
        meetingPlace.setText(meeting.getPlace());
//        meetingDate.setText(meeting.getDate().toString());
//        meetingTime.setText(meeting.getTime().toString());
//        amountParticipants.setText(meeting.getAmountParticipants().toString());

        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(AppIntents.MEETING_SHOW);
            intent.putExtra(Meeting.class.getCanonicalName(), meeting);
            context.startActivity(intent);
        });

        return convertView;
    }

    private Meeting getMeeting(int position) {
        return ((Meeting) getItem(position));
    }

}





























