package ru.bur.cometogetherandroid.task;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class AuthorizationTask extends AsyncTask<String, Void, String> {
    private String LOG_TAG = "cometogetherandroid ";


    private final String CONNECTION_URL = "http://10.0.2.2:8080/rest/android";

    @Override
    protected String doInBackground(String... strings) {
        RestTemplate restTemplate = new RestTemplate();
        String phoneNumber = strings[0];
        Log.d("LOG_TAG", "phoneNumber="+phoneNumber);
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String result = restTemplate.postForObject(CONNECTION_URL, phoneNumber, String.class);
        return result;
    }

}


