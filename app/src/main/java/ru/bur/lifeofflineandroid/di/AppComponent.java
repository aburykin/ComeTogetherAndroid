package ru.bur.lifeofflineandroid.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.bur.lifeofflineandroid.activities.authorization.Authorization;
import ru.bur.lifeofflineandroid.activities.authorization.AutorizationPresender;
import ru.bur.lifeofflineandroid.activities.createMeeting.CreateMeeting;
import ru.bur.lifeofflineandroid.activities.createMeeting.CreateMeetingPresender;
import ru.bur.lifeofflineandroid.activities.meetingScroller.MeetingScroller;
import ru.bur.lifeofflineandroid.activities.meetingScroller.MeetingScrollerPresender;
import ru.bur.lifeofflineandroid.common.Cookies;
import ru.bur.lifeofflineandroid.network.AddCookiesInterceptor;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(Cookies cookies);

    void inject(AutorizationPresender presender);
    void inject(MeetingScrollerPresender presender);
    void inject(CreateMeetingPresender presender);

    void inject(AddCookiesInterceptor interceptor);

    void inject(Authorization activity);
    void inject(MeetingScroller activity);
    void inject(CreateMeeting activity);

}
