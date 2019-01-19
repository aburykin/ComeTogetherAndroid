package ru.bur.cometogetherandroid.activities.meetingScroller;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.bur.cometogetherandroid.ComeTogetherApp;
import ru.bur.cometogetherandroid.common.Cookies;
import ru.bur.cometogetherandroid.model.Meeting;
import ru.bur.dto.MeetingDto;

import static ru.bur.cometogetherandroid.util.MainLogger.debug;
import static ru.bur.cometogetherandroid.util.MainLogger.error;

public class MeetingScrollerPresender {

    private String LOG_TAG = "MeetingScrollerPresender";
    private MeetingScroller view;
    private Cookies cookies;


    @Inject
    public MeetingScrollerPresender(Cookies cookies) {
        this.cookies = cookies;
    }

    public void attachView(MeetingScroller activity) {
        view = activity;
    }

    public void getMeetings(List<Meeting> meetings) {

        ComeTogetherApp.getApi().getAllMeetings().enqueue(new Callback<List<MeetingDto>>() {
            @Override
            public void onResponse(Call<List<MeetingDto>> call, Response<List<MeetingDto>> response) {
                debug(LOG_TAG, "getMeetings(): response=" + response);
                List<MeetingDto> meetingDtos = response.body();

                meetingDtos.forEach(x -> {
                    Meeting meeting = new Meeting();
                    meeting.setId(x.getId());
                    meeting.setName(x.getName());
                    meeting.setPlace(x.getPlace());
                    meeting.setDescription(x.getDescription());
                    meetings.add(meeting);
                });

                view.updateMeetingScroller();

            }

            @Override
            public void onFailure(Call<List<MeetingDto>> call, Throwable t) {
                error(LOG_TAG, "getMeetings(): Throwable=" + t);
            }
        });


    }
}

     /*   for (int i = 0; i < 3; i++) {
            meetings.add(new Meeting("Волейбол с друзьями", "Площадка во дворе", LocalDate.of(2018, 11, 5), LocalTime.of(18, 0), i));
        }
        return meetings;
        */
