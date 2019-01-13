package ru.bur.cometogetherandroid.activities.meetingScroller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.bur.cometogetherandroid.common.Cookies;
import ru.bur.cometogetherandroid.model.Meeting;

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

    public List<Meeting> getMeetings() {
        List<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            meetings.add(new Meeting("Волейбол с друзьями", "Площадка во дворе", LocalDate.of(2018, 11, 5), LocalTime.of(18, 0), i));
        }
        return meetings;
/*

        ComeTogetherApp.getApi().tryAuth(authDto).enqueue(new Callback<AppUserDto>() {
            @Override
            public void onResponse(Call<AppUserDto> call, Response<AppUserDto> response) {
             //   debug(LOG_TAG, "tryAutorization(): response=" + response);
                AppUserDto dto = response.body();
                if (dto != null) {
                    cookies.set(CookiesEnum.token.toString(), dto.getAuthorizationToken());
                    view.completeAuthorizationSuccess(dto.getAuthorizationToken());
                } else {
                    view.completeAuthorizationFault("Авторизоваться не удалось: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<AppUserDto> call, Throwable t) {
              //  error(LOG_TAG, "tryAutorization(): Throwable=" + t);
                view.completeAuthorizationFault("Авторизоваться не удалось: " + t.getLocalizedMessage());
            }
        });

        */
    }
}
