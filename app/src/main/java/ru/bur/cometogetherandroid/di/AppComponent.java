package ru.bur.cometogetherandroid.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.bur.cometogetherandroid.activities.authorization.Authorization;
import ru.bur.cometogetherandroid.activities.authorization.AutorizationPresender;
import ru.bur.cometogetherandroid.activities.meetingScroller.MeetingScroller;
import ru.bur.cometogetherandroid.activities.meetingScroller.MeetingScrollerPresender;
import ru.bur.cometogetherandroid.common.Cookies;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(Cookies cookies);

    void inject(AutorizationPresender presender);
    void inject(MeetingScrollerPresender presender);

    void inject(Authorization activity);
    void inject(MeetingScroller activity);

}
