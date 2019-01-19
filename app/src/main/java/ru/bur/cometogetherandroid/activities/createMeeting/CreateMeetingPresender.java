package ru.bur.cometogetherandroid.activities.createMeeting;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.bur.cometogetherandroid.ComeTogetherApp;
import ru.bur.dto.MeetingDto;

import static ru.bur.cometogetherandroid.util.MainLogger.debug;
import static ru.bur.cometogetherandroid.util.MainLogger.error;

public class CreateMeetingPresender {

    private String LOG_TAG = "CreateMeetingPresender";
    private CreateMeeting view;


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
}
