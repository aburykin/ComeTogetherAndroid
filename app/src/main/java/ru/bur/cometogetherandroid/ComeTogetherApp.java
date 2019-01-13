package ru.bur.cometogetherandroid;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.bur.cometogetherandroid.common.Cookies;
import ru.bur.cometogetherandroid.di.AppComponents;
import ru.bur.cometogetherandroid.di.AppModule;
import ru.bur.cometogetherandroid.di.DaggerAppComponents;
import ru.bur.cometogetherandroid.network.ComeTogetherServerApi;

public class ComeTogetherApp extends Application {

    private static ComeTogetherServerApi comeTogetherServerApi;
    private Retrofit retrofit;
    private String baseUrl = "http://192.168.0.102:8080";

    //  private AppComponents appComponents;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl) //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build(); //Создаем объект, при помощи которого будем выполнять запросы

        comeTogetherServerApi = retrofit.create(ComeTogetherServerApi.class);

        app = this;
        mAppComponents = DaggerAppComponents.builder().appModule(new AppModule(getApplicationContext())).build();
        //appComponents.inject(new Cookies(this));
    }


    public static ComeTogetherServerApi getApi() {
        return comeTogetherServerApi;
    }

    private AppComponents mAppComponents;
    public ComeTogetherApp app;

    public AppComponents getAppComponents() {
        return mAppComponents;
    }


}
