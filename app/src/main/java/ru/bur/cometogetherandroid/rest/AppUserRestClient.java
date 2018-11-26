package ru.bur.cometogetherandroid.rest;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.StringEntity;
import ru.bur.dto.AuthDto;

public class AppUserRestClient {

    //private static final String BASE_URL = "http://192.168.0.101:8080/user/auth";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void authorization(Context context, AuthDto authDto) {
        String url = "http://192.168.0.101:8080/user/auth";

        StringEntity httpEntity = null;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phoneNumber",authDto.getPhoneNumber());

            httpEntity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AppUserResponseHandler responseHandler = new AppUserResponseHandler();
        RequestHandle requestHandle = client.post(context, url, httpEntity, ContentType.APPLICATION_JSON.toString(), responseHandler);
        System.out.println();

    }
/*
    public static void get(String phoneNumber) {
        client.get("http://192.168.0.101:8080/appUsers/", null, new AppUserResponseHandler());
    }

    */
/*
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        url = "";
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }
    */
/*
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
*/
}


