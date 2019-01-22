package ru.bur.cometogetherandroid.activities.createMeeting;


import android.content.Intent;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.bur.cometogetherandroid.ComeTogetherApp;
import ru.bur.cometogetherandroid.common.AppIntents;
import ru.bur.cometogetherandroid.common.Cookies;
import ru.bur.cometogetherandroid.model.Meeting;
import ru.bur.dto.MeetingDto;

import static ru.bur.cometogetherandroid.util.MainLogger.debug;
import static ru.bur.cometogetherandroid.util.MainLogger.error;

public class CreateMeetingPresender {

    private String LOG_TAG = "CreateMeetingPresender";
    private CreateMeeting view;
    private Cookies cookies;

    @Inject
    public CreateMeetingPresender(Cookies cookies) {
        this.cookies = cookies;
    }

    void attachView(CreateMeeting activity) {
        view = activity;
    }

    public void createMeeting(MeetingDto meetingDto) {
        ComeTogetherApp.getApi().createMeeting(meetingDto).enqueue(new Callback<MeetingDto>() {
            @Override
            public void onResponse(Call<MeetingDto> call, Response<MeetingDto> response) {
                debug(LOG_TAG, "createMeeting(): response=" + response);
                //  MeetingDto dto = response.body();
                view.goToMeetingScroller();
            }

            @Override
            public void onFailure(Call<MeetingDto> call, Throwable t) {
                error(LOG_TAG, "createMeeting(): Throwable=" + t);
            }
        });
    }

    void updateMeeting(MeetingDto meetingDto) {
        ComeTogetherApp.getApi().updateMeeting(meetingDto).enqueue(new Callback<MeetingDto>() {
            @Override
            public void onResponse(Call<MeetingDto> call, Response<MeetingDto> response) {
                debug(LOG_TAG, "updateMeeting(): response=" + response);
                view.goToMeetingScroller();
            }

            @Override
            public void onFailure(Call<MeetingDto> call, Throwable t) {
                error(LOG_TAG, "updateMeeting(): Throwable=" + t);
            }
        });
    }

    void deleteMeeting(Meeting meeting) {
        ComeTogetherApp.getApi().deleteMeeting(meeting.getMeetingId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                error(LOG_TAG, "deleteMeeting(): Throwable=" + t);
            }
        });
    }

    public void setVisibleMenu(Meeting meeting) {
        ComeTogetherApp.getApi().getMeetingOwners(meeting.getMeetingId()).enqueue(new Callback<List<Long>>() {
            @Override
            public void onResponse(Call<List<Long>> call, Response<List<Long>> response) {
                List<Long> owners = response.body();

                boolean isOwner = owners.contains(cookies.getUserId());
                view.updateIsOwner(isOwner);

                if (isOwner) {
                    view.leaveBtn.setVisibility(View.GONE);
                    view.joinBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Long>> call, Throwable t) {
                error(LOG_TAG, "setVisiableMenu(): Throwable=" + t);
            }
        });
    }

    void tuneActivityMode() {
        Intent intent = view.getIntent();
        String action = intent.getAction();
        if (action.equals(AppIntents.MEETING_CREATE)) {
            view.setTitle("Новая встреча");
            view.setUseMenuGroup(MenuGroup.VISIBLE_CREATE);
            view.saveBtn.setVisibility(View.VISIBLE);
            view.setAllEditableTrue();
            view.setMeeting(new Meeting());
            view.updateIsOwner(true);
        } else if (action.equals(AppIntents.MEETING_SHOW)) {
            view.setTitle("Просмотр встречи");
            view.setUseMenuGroup(MenuGroup.VISIBLE_SHOW);
            view.saveBtn.setVisibility(View.GONE);
            view.setAllEditableFalse();
            Meeting meeting = intent.getParcelableExtra(Meeting.class.getCanonicalName());
            getMeeting(meeting);
        }
    }


    void addParticipant(Meeting meeting) {
        ComeTogetherApp.getApi().addParticipant(meeting.getMeetingId(), cookies.getUserId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                setVisibleButtons(meeting);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                error(LOG_TAG, "addParticipant(): Throwable=" + t);
            }
        });
    }

    void deleteParticipant(Meeting meeting) {
        ComeTogetherApp.getApi().deleteParticipant(meeting.getMeetingId(), cookies.getUserId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                setVisibleButtons(meeting);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                error(LOG_TAG, "deleteParticipant(): Throwable=" + t);
            }
        });
    }

    private void getMeeting(Meeting meeting) {
        ComeTogetherApp.getApi().getMeeting(meeting.getMeetingId()).enqueue(new Callback<MeetingDto>() {
            @Override
            public void onResponse(Call<MeetingDto> call, Response<MeetingDto> response) {
                MeetingDto meetingDto = response.body();
                Meeting meeting = new Meeting();

                meeting.setMeetingId(meetingDto.getMeetingId());
                meeting.setName(meetingDto.getName());
                meeting.setPlace(meetingDto.getPlace());
                //meeting.setDate(meetingDto.get());
                //meeting.setTime(meetingDto.get());
                meeting.setDescription(meetingDto.getDescription());
                //meeting.setAmountParticipants(meetingDto.get());

                view.setMeeting(meeting);
                view.fillDataFromIntent(meeting);
                setVisibleButtons(meeting);
                setVisibleMenu(meeting);
            }

            @Override
            public void onFailure(Call<MeetingDto> call, Throwable t) {
                error(LOG_TAG, "getMeeting(): Throwable=" + t);
            }
        });
    }

    private void setVisibleButtons(Meeting meeting) {
        ComeTogetherApp.getApi().getMeetingParticipants(meeting.getMeetingId()).enqueue(new Callback<List<Long>>() {
            @Override
            public void onResponse(Call<List<Long>> call, Response<List<Long>> response) {
                List<Long> participants = response.body();
                if (participants.contains(cookies.getUserId())) {
                    view.joinBtn.setVisibility(View.GONE);
                    view.leaveBtn.setVisibility(View.VISIBLE);
                } else {
                    view.joinBtn.setVisibility(View.VISIBLE);
                    view.leaveBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Long>> call, Throwable t) {
                error(LOG_TAG, "setVisibleButtons(): Throwable=" + t);
            }
        });
    }


}






























