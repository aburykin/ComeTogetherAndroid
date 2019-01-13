package ru.bur.cometogetherandroid.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.bur.cometogetherandroid.activities.authorization.AutorizationPresender;
import ru.bur.cometogetherandroid.common.Cookies;

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


}
