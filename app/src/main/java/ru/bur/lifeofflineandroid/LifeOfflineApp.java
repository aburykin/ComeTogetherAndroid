package ru.bur.lifeofflineandroid;

import android.app.Application;

import io.objectbox.BoxStore;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.bur.lifeofflineandroid.common.Cookies;
import ru.bur.lifeofflineandroid.di.AppComponent;
import ru.bur.lifeofflineandroid.di.AppModule;
import ru.bur.lifeofflineandroid.di.DaggerAppComponent;
import ru.bur.lifeofflineandroid.model.MyObjectBox;
import ru.bur.lifeofflineandroid.network.AddCookiesInterceptor;
import ru.bur.lifeofflineandroid.network.LifeOfflineServerApi;

public class LifeOfflineApp extends Application {

    private static LifeOfflineServerApi serverApi;
    private Retrofit retrofit;
    private String baseUrl = "http://192.168.0.102:8080";
    // private String baseUrl = "http://192.168.0.86:8080";
    private AppComponent appComponent;
    private BoxStore boxStore;

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

        serverApi = retrofit.create(LifeOfflineServerApi.class);

        // настраиваем взаимодействие с БД
        boxStore = MyObjectBox.builder().androidContext(this).build();

    }

    public static LifeOfflineServerApi getApi() {
        return serverApi;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public BoxStore getBoxStore() {
        if (boxStore == null) {
            boxStore = MyObjectBox.builder().androidContext(this).build();
        }
        return boxStore;
    }


}
