package ru.bur.cometogetherandroid.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.bur.cometogetherandroid.ComeTogetherApp;
import ru.bur.cometogetherandroid.activities.authorization.AutorizationPresender;
import ru.bur.cometogetherandroid.activities.createMeeting.CreateMeetingPresender;
import ru.bur.cometogetherandroid.activities.meetingScroller.MeetingScrollerPresender;
import ru.bur.cometogetherandroid.common.Cookies;
import ru.bur.cometogetherandroid.network.AddCookiesInterceptor;

@Module
public class AppModule {

    private Context mainContext;

    public AppModule(Context mainContext) {
        this.mainContext = mainContext;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mainContext;
    }

    @Provides
    @Singleton
    Cookies cookies() {
        return new Cookies(mainContext);
    }

    @Provides
    @Singleton
    AutorizationPresender autorizationPresender(Cookies cookies) {
        return new AutorizationPresender(cookies);
    }

    @Provides
    @Singleton
    MeetingScrollerPresender meetingScrollerPresender(Cookies cookies) {
        return new MeetingScrollerPresender(cookies);
    }

    @Provides
    @Singleton
    CreateMeetingPresender createMeetingPresender() {
        return new CreateMeetingPresender();
    }

    @Provides
    @Singleton
    AddCookiesInterceptor addCookiesInterceptor(Cookies cookies) {
        return new AddCookiesInterceptor(cookies);
    }




}
