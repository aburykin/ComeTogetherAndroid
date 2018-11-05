package ru.bur.cometogetherandroid.task;

import android.os.AsyncTask;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class AskServer extends AsyncTask<String, Void, Void> {

    private final String CONNECTION_URL = "http://10.0.2.2:8080/rest/android";

    @Override
    protected Void doInBackground(String... strings) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String result = restTemplate.postForObject(CONNECTION_URL, strings[0], String.class);
        return null;
    }
}