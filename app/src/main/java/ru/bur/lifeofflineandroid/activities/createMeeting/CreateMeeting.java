package ru.bur.lifeofflineandroid.activities.createMeeting;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.bur.dto.MeetingDto;
import ru.bur.lifeofflineandroid.LifeOfflineApp;
import ru.bur.lifeofflineandroid.R;
import ru.bur.lifeofflineandroid.activities.meetingScroller.MeetingScroller;
import ru.bur.lifeofflineandroid.model.MapperMeetingDto;
import ru.bur.lifeofflineandroid.model.Meeting;

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

    @BindView(R.id.saveBtn)
    Button saveBtn;

    @BindView(R.id.joinBtn)
    Button joinBtn;

    @BindView(R.id.leaveBtn)
    Button leaveBtn;


    @Inject
    CreateMeetingPresender createMeetingPresender;

    private Meeting meeting;
    private int useMenuGroup = MenuGroup.INVISIBLE_MENU;
    private boolean isOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_meeting);
        ButterKnife.bind(this);
        ((LifeOfflineApp) getApplicationContext()).getAppComponent().inject(this);
        createMeetingPresender.attachView(this);
        createMeetingPresender.tuneActivityMode();
        saveBtn.setOnClickListener(view -> {
            Meeting meetingForShow = new Meeting();
            meetingForShow.setMeetingId(meeting.getMeetingId());
            meetingForShow.setName(meetingName.getText().toString());
            meetingForShow.setPlace(meetingPlace.getText().toString());
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("d.M.y");
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("H:m");
            meetingForShow.setDate(LocalDate.parse(meetingDate.getText(), formatterDate));
            meetingForShow.setTime(LocalTime.parse(meetingTime.getText(), formatterTime));
            meetingForShow.setDescription(meetingDescription.getText().toString());
            MeetingDto meetingDto  =  MapperMeetingDto.toDto(meetingForShow);
            if (meeting.getMeetingId() == null) {
                createMeetingPresender.createMeeting(meetingDto);
            } else {
                createMeetingPresender.updateMeeting(meetingDto);
            }
        });

        joinBtn.setOnClickListener(view -> {
            createMeetingPresender.addParticipant(meeting);
        });

        leaveBtn.setOnClickListener(view -> {
            createMeetingPresender.deleteParticipant(meeting);
        });
    }



    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        if (useMenuGroup == MenuGroup.VISIBLE_SHOW && isOwner) {
            menu.add(MenuGroup.VISIBLE_SHOW, 1, 1, "Редактировать встречу");
            menu.add(MenuGroup.VISIBLE_SHOW, 2, 2, "Удалить встречу");
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                this.setTitle("Редактирование");
                setAllEditableTrue();
                saveBtn.setVisibility(View.VISIBLE);
                useMenuGroup = MenuGroup.VISIBLE_EDIT;
                this.invalidateOptionsMenu();
                break;
            case 2:
                createMeetingPresender.deleteMeeting(meeting);
                goToMeetingScroller();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void updateIsOwner(boolean value) {
        isOwner = value;
        invalidateOptionsMenu();
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

    private DatePickerDialog.OnDateSetListener dateCallBackListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            meetingDate.setText(String.format("%d.%d.%d", dayOfMonth, monthOfYear, year));
        }
    };

    private TimePickerDialog.OnTimeSetListener timeCallBackListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            meetingTime.setText(String.format("%d:%d", hourOfDay, minute));
        }
    };

    void goToMeetingScroller() {
        Intent intent = new Intent(this, MeetingScroller.class);
        startActivity(intent);
    }


    void fillDataFromIntent(Meeting meeting) {
        meetingName.setText(meeting.getName());
        meetingPlace.setText(meeting.getPlace());
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        meetingDate.setText( meeting.getDate().format(formatterDate));
        meetingTime.setText(meeting.getTime().format(formatterTime));
        meetingDescription.setText(meeting.getDescription());
    }

    void setAllEditableFalse() {
        changeEditable(meetingName, false);
        changeEditable(meetingPlace, false);
        changeEditable(meetingDate, false);
        changeEditable(meetingTime, false);
        changeEditable(meetingDescription, false);
        meetingDate.setOnClickListener(null);
        meetingTime.setOnClickListener(null);
    }

    void setAllEditableTrue() {
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

    void setUseMenuGroup(int useMenuGroup) {
        this.useMenuGroup = useMenuGroup;
    }

    void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

}
