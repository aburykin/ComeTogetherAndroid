package ru.bur.cometogetherandroid.activities.createMeeting;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.bur.cometogetherandroid.ComeTogetherApp;
import ru.bur.cometogetherandroid.R;
import ru.bur.cometogetherandroid.activities.meetingScroller.MeetingScroller;
import ru.bur.cometogetherandroid.common.AppIntents;
import ru.bur.cometogetherandroid.model.Meeting;
import ru.bur.dto.MeetingDto;

public class CreateMeeting extends AppCompatActivity {

    private final int DIALOG_DATE = 1;
    private final int DIALOG_TIME = 2;

    @BindView(R.id.meetingName)
    TextInputEditText meetingName;

    @BindView(R.id.meetingPlace)
    TextInputEditText meetingPlace;

    @BindView(R.id.meetingDate)
    TextInputEditText meetingDate;

    @BindView(R.id.meetingTime)
    TextInputEditText meetingTime;

    @BindView(R.id.meetingDescription)
    TextInputEditText meetingDescription;

    @BindView(R.id.createNewMeeting)
    Button createNewMeeting;

    @Inject
    CreateMeetingPresender createMeetingPresender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_meeting);
        ButterKnife.bind(this);
        ((ComeTogetherApp) getApplicationContext()).getAppComponent().inject(this);
        createMeetingPresender.attachView(this);

        setActivityToMode();

        createNewMeeting.setOnClickListener(view -> {
            MeetingDto meetingDto = new MeetingDto();
            meetingDto.setName(meetingName.getText().toString());
            meetingDto.setPlace(meetingPlace.getText().toString());
            meetingDto.setDescription(meetingDescription.getText().toString());
            createMeetingPresender.createMeeting(meetingDto);
        });
    }

    private void setActivityToMode() {
        Intent intent = getIntent();
        String action = intent.getAction();

        if (action.equals(AppIntents.MEETING_CREATE)) {
            this.setTitle("Новая встреча");
            setAllEditableTrue();
            createNewMeeting.setVisibility(View.VISIBLE);
        } else if (action.equals(AppIntents.MEETING_EDIT)) {
            this.setTitle("Редактировать встречу");
            fillDataFromIntent(intent);
            setAllEditableTrue();
        } else if (action.equals(AppIntents.MEETING_SHOW)) {
            this.setTitle("Просмотр встречи");
            fillDataFromIntent(intent);
            setAllEditableFalse();
            createNewMeeting.setVisibility(View.GONE);
        }
    }


    private void fillDataFromIntent(Intent intent) {
        Meeting meeting = intent.getParcelableExtra(Meeting.class.getCanonicalName());
        meetingName.setText(meeting.getName());
        meetingPlace.setText(meeting.getPlace());
    }

    private void setAllEditableFalse() {
        changeEditable(meetingName, false);
        changeEditable(meetingPlace, false);
        changeEditable(meetingDate, false);
        changeEditable(meetingTime, false);
        changeEditable(meetingDescription, false);
        meetingDate.setOnClickListener(null);
        meetingTime.setOnClickListener(null);
    }

    private void setAllEditableTrue() {
        changeEditable(meetingName, true);
        changeEditable(meetingPlace, true);
        changeEditable(meetingDate, false);
        changeEditable(meetingTime, false);
        changeEditable(meetingDescription, true);
        meetingDate.setOnClickListener(view -> showDialog(DIALOG_DATE));
        meetingTime.setOnClickListener(view -> showDialog(DIALOG_TIME));

    }

    /**
     * @param editText   - элемент над которым производим действие
     * @param isEditable - сделать его редактируемым true, иначе false
     */
    private void changeEditable(TextInputEditText editText, boolean isEditable) {
        editText.setClickable(isEditable);
        editText.setCursorVisible(isEditable);
        editText.setFocusable(isEditable);
        editText.setFocusableInTouchMode(isEditable);
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


    public void goToMeetingScroller() {
        Intent intent = new Intent(this, MeetingScroller.class);
        startActivity(intent);
    }

}
