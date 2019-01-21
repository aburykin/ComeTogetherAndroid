package ru.bur.cometogetherandroid.activities.createMeeting;


import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.bur.cometogetherandroid.ComeTogetherApp;
import ru.bur.cometogetherandroid.common.Cookies;
import ru.bur.cometogetherandroid.common.CookiesEnum;
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


    public void attachView(CreateMeeting activity) {
        view = activity;
    }


    public void createMeeting(MeetingDto meetingDto) {
        ComeTogetherApp.getApi().createMeeting(meetingDto).enqueue(new Callback<MeetingDto>() {
            @Override
            public void onResponse(Call<MeetingDto> call, Response<MeetingDto> response) {
                debug(LOG_TAG, "createMeeting(): response=" + response);
                MeetingDto dto = response.body();
                System.out.println("response = " + dto);
                view.goToMeetingScroller();
            }

            @Override
            public void onFailure(Call<MeetingDto> call, Throwable t) {
                error(LOG_TAG, "createMeeting(): Throwable=" + t);
            }
        });
    }

    public void setVisiableMenu(Meeting meeting) {
        Long userId = Long.valueOf(cookies.get(CookiesEnum.user_id.toString()));
        ComeTogetherApp.getApi().getMeetingOwners(meeting.getMeetingId()).enqueue(new Callback<List<Long>>() {
            @Override
            public void onResponse(Call<List<Long>> call, Response<List<Long>> response) {
                List<Long> owners = response.body();
                if (owners.contains(userId)) {
                    view.setVisiableMenu();
                } else {
                    view.setUnvisiableMenu();
                }
            }

            @Override
            public void onFailure(Call<List<Long>> call, Throwable t) {
                error(LOG_TAG, "setVisiableMenu(): Throwable=" + t);
            }
        });
    }

    public void deleteMeeting(Meeting meeting){
        ComeTogetherApp.getApi().deleteMeeting(meeting.getMeetingId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {}

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                error(LOG_TAG, "deleteMeeting(): Throwable=" + t);
            }
        });
    }
}
