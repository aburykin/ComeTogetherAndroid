package ru.bur.cometogetherandroid;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.bur.cometogetherandroid.di.AppComponent;
import ru.bur.cometogetherandroid.di.AppModule;
import ru.bur.cometogetherandroid.di.DaggerAppComponent;
import ru.bur.cometogetherandroid.network.ComeTogetherServerApi;

public class ComeTogetherApp extends Application {

    private static ComeTogetherServerApi comeTogetherServerApi;
    private Retrofit retrofit;
    private String baseUrl = "http://192.168.0.102:8080";
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Создаем объект, при помощи которого будем выполнять запросы
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();

        comeTogetherServerApi = retrofit.create(ComeTogetherServerApi.class);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public static ComeTogetherServerApi getApi() {
        return comeTogetherServerApi;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


}
