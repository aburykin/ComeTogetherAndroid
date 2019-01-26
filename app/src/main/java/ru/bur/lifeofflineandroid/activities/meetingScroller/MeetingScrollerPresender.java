package ru.bur.lifeofflineandroid.activities.meetingScroller;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.bur.lifeofflineandroid.LifeOfflineApp;
import ru.bur.lifeofflineandroid.common.Cookies;
import ru.bur.lifeofflineandroid.common.CookiesEnum;
import ru.bur.lifeofflineandroid.model.Meeting;
import ru.bur.dto.MeetingDto;
import ru.bur.lifeofflineandroid.util.MainLogger;

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

        LifeOfflineApp.getApi().getAllMeetings().enqueue(new Callback<List<MeetingDto>>() {
            @Override
            public void onResponse(Call<List<MeetingDto>> call, Response<List<MeetingDto>> response) {
                MainLogger.debug(LOG_TAG, "getMeetings(): response=" + response);

                // если не авторизован, то вернуться на страницу авторизации.
                if ( response.isSuccessful() == false){
                    if ( response.code() == HttpStatus.UNAUTHORIZED.value()){
                        cookies.set(CookiesEnum.token.toString(), null);
                        view.goToAuthorization();
                    }
                } else {
                    List<MeetingDto> meetingDtos = response.body();
                    meetingDtos.forEach(x -> {
                        Meeting meeting = new Meeting();
                        meeting.setMeetingId(x.getMeetingId());
                        meeting.setName(x.getName());
                        meeting.setPlace(x.getPlace());

                        Timestamp datetime = x.getStartDate();

                      //  LocalDate.

                     //   meeting.setDate();
                     //   meeting.setTime(x.get);
                        meeting.setDescription(x.getDescription());
                        meetings.add(meeting);
                    });
                    view.updateMeetingScroller();
                }
            }

            @Override
            public void onFailure(Call<List<MeetingDto>> call, Throwable t) {
                MainLogger.error(LOG_TAG, "getMeetings(): Throwable=" + t);
            }
        });
    }
}

     /*   for (int i = 0; i < 3; i++) {
            meetings.add(new Meeting("Волейбол с друзьями", "Площадка во дворе", LocalDate.of(2018, 11, 5), LocalTime.of(18, 0), i));
        }
        return meetings;
        */
