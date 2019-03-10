package ru.bur.lifeofflineandroid.activities.meetingScroller;

import android.view.View;

import org.springframework.http.HttpStatus;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.bur.dto.MeetingDto;
import ru.bur.lifeofflineandroid.LifeOfflineApp;
import ru.bur.lifeofflineandroid.common.Cookies;
import ru.bur.lifeofflineandroid.common.CookiesEnum;
import ru.bur.lifeofflineandroid.model.MapperMeetingDto;
import ru.bur.lifeofflineandroid.model.Meeting;
import ru.bur.lifeofflineandroid.util.MainLogger;

public class MeetingScrollerPresender {

    private String LOG_TAG = this.getClass().getName();
    private MeetingScroller view;
    private Cookies cookies;

    @Inject
    public MeetingScrollerPresender(Cookies cookies) {
        this.cookies = cookies;
    }

    public void attachView(MeetingScroller activity) {
        view = activity;
    }

    public void getFirstNmeatings(List<Meeting> meetings) {
        meetings.clear();
        LifeOfflineApp.getApi().getFirstNmeatings().enqueue(new Callback<List<MeetingDto>>() {
            @Override
            public void onResponse(Call<List<MeetingDto>> call, Response<List<MeetingDto>> response) {
                MainLogger.debug(LOG_TAG, "getFirstNmeatings(): response=" + response);

                // если не авторизован, то вернуться на страницу авторизации.
                if (response.isSuccessful() == false) {
                    if (response.code() == HttpStatus.UNAUTHORIZED.value()) {
                        cookies.set(CookiesEnum.token.toString(), null);
                        view.goToAuthorization();
                    }
                } else {
                    List<MeetingDto> meetingDtos = response.body();
                    meetingDtos.forEach(dto -> {
                        Meeting meeting = MapperMeetingDto.toModel(dto);
                        meetings.add(meeting);
                    });
                    view.updateMeetingScroller();
                    view.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<MeetingDto>> call, Throwable t) {
                MainLogger.error(LOG_TAG, "getFirstNmeatings(): Throwable=" + t);
            }
        });
    }

    public void getNextMeetings(List<Meeting> currMeetings, MeetingDto lastMeetingDto) {
        LifeOfflineApp.getApi().getNextMeetings(lastMeetingDto).enqueue(new Callback<List<MeetingDto>>() {
            @Override
            public void onResponse(Call<List<MeetingDto>> call, Response<List<MeetingDto>> response) {
                MainLogger.debug(LOG_TAG, "getNextMeetings(): response=" + response);

                // если не авторизован, то вернуться на страницу авторизации.
                if (response.isSuccessful() == false) {
                    if (response.code() == HttpStatus.UNAUTHORIZED.value()) {
                        cookies.set(CookiesEnum.token.toString(), null);
                        view.goToAuthorization();
                    }
                } else {
                    List<MeetingDto> nextMeetingDtos = response.body();
                    mergeNextMeetings(currMeetings, nextMeetingDtos);
                    view.updateMeetingScroller();
                    view.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<MeetingDto>> call, Throwable t) {
                MainLogger.error(LOG_TAG, "getNextMeetings(): Throwable=" + t);
            }
        });
    }


    private void mergeNextMeetings(List<Meeting> currMeetings, List<MeetingDto> nextMeetingDtos) {
        nextMeetingDtos.stream().forEach(dto -> {
            Meeting meeting = MapperMeetingDto.toModel(dto);
            if (currMeetings.stream().anyMatch(curr -> curr.getMeetingId().equals(meeting.getMeetingId()))) { //REPLACE
                for (Meeting curr : currMeetings) {
                    if (curr.getMeetingId().equals(meeting.getMeetingId())) {
                        int index = currMeetings.indexOf(curr);
                        currMeetings.remove(index);
                        currMeetings.add(index, meeting);
                        break;
                    }
                }
            } else {
                currMeetings.add(meeting);
            }

        });
    }

    public void getOnlyUsersMeetings(List<Meeting> currMeetings) {
        LifeOfflineApp.getApi().getMeetingsFilteredByUser().enqueue(new Callback<List<MeetingDto>>() {
            @Override
            public void onResponse(Call<List<MeetingDto>> call, Response<List<MeetingDto>> response) {
                MainLogger.debug(LOG_TAG, "getMeetingsFilteredByUser(): response=" + response);

                // если не авторизован, то вернуться на страницу авторизации.
                if (response.isSuccessful() == false) {
                    if (response.code() == HttpStatus.UNAUTHORIZED.value()) {
                        cookies.set(CookiesEnum.token.toString(), null);
                        view.goToAuthorization();
                    }
                } else {
                    currMeetings.clear();
                    List<MeetingDto> nextMeetingDtos = response.body();
                    mergeNextMeetings(currMeetings, nextMeetingDtos);
                    view.updateMeetingScroller();
                    view.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<MeetingDto>> call, Throwable t) {
                MainLogger.error(LOG_TAG, "getMeetingsFilteredByUser(): Throwable=" + t);
            }
        });

    }

}