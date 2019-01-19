package ru.bur.cometogetherandroid;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.bur.cometogetherandroid.common.Cookies;
import ru.bur.cometogetherandroid.di.AppComponent;
import ru.bur.cometogetherandroid.di.AppModule;
import ru.bur.cometogetherandroid.di.DaggerAppComponent;
import ru.bur.cometogetherandroid.network.AddCookiesInterceptor;
import ru.bur.cometogetherandroid.network.ComeTogetherServerApi;
import ru.bur.cometogetherandroid.network.NullOnEmptyConverterFactory;

public class ComeTogetherApp extends Application {

    private static ComeTogetherServerApi comeTogetherServerApi;
    private Retrofit retrofit;
    private String baseUrl = "http://192.168.0.102:8080";
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        Cookies cookies = new Cookies(this);
        appComponent.inject(cookies);

        //httpClient - это конструктор запросов, по факту используется для добавления cookies
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addNetworkInterceptor(new AddCookiesInterceptor(cookies));

        // Создаем объект, при помощи которого будем выполнять запросы
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
            //    .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        comeTogetherServerApi = retrofit.create(ComeTogetherServerApi.class);


    }

    public static ComeTogetherServerApi getApi() {
        return comeTogetherServerApi;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


}
