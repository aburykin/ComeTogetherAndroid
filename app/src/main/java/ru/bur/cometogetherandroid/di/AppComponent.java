package ru.bur.cometogetherandroid.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.bur.cometogetherandroid.activities.authorization.Authorization;
import ru.bur.cometogetherandroid.activities.authorization.AutorizationPresender;
import ru.bur.cometogetherandroid.activities.createMeeting.CreateMeeting;
import ru.bur.cometogetherandroid.activities.createMeeting.CreateMeetingPresender;
import ru.bur.cometogetherandroid.activities.meetingScroller.MeetingScroller;
import ru.bur.cometogetherandroid.activities.meetingScroller.MeetingScrollerPresender;
import ru.bur.cometogetherandroid.common.Cookies;
import ru.bur.cometogetherandroid.network.AddCookiesInterceptor;

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
