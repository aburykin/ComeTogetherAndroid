package ru.bur.lifeofflineandroid.network;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import ru.bur.lifeofflineandroid.common.Cookies;
import ru.bur.lifeofflineandroid.common.CookiesEnum;

public class AddCookiesInterceptor implements Interceptor {

    private Cookies cookies;

    @Inject
    public AddCookiesInterceptor(Cookies cookies) {
        this.cookies = cookies;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = cookies.get(CookiesEnum.token.toString());

        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Cookie", "user_token="+token);
        return chain.proceed(builder.build());
    }
}
