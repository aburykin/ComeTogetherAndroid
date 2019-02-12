package ru.bur.lifeofflineandroid.di;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.bur.lifeofflineandroid.activities.authorization.AutorizationPresender;
import ru.bur.lifeofflineandroid.activities.createMeeting.CreateMeetingPresender;
import ru.bur.lifeofflineandroid.activities.meetingScroller.MeetingScrollerPresender;
import ru.bur.lifeofflineandroid.common.Cookies;
import ru.bur.lifeofflineandroid.dao.ObjectBox;
import ru.bur.lifeofflineandroid.dao.objectBox.ObjectBoxImpl;
import ru.bur.lifeofflineandroid.network.AddCookiesInterceptor;

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
    MeetingScrollerPresender meetingScrollerPresender(Cookies cookies, ObjectBox objectBox) {
        return new MeetingScrollerPresender(cookies, objectBox);
    }

    @Provides
    @Singleton
    CreateMeetingPresender createMeetingPresender(Cookies cookies, ObjectBox objectBox) {
        return new CreateMeetingPresender(cookies, objectBox);
    }

    @Provides
    @Singleton
    AddCookiesInterceptor addCookiesInterceptor(Cookies cookies) {
        return new AddCookiesInterceptor(cookies);
    }

    @Singleton
    @NonNull
    @Provides
    ObjectBox objectBox() {
        return new ObjectBoxImpl(mainContext);
    }


}
