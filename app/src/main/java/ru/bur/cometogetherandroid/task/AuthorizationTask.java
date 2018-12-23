package ru.bur.cometogetherandroid.task;

import android.os.AsyncTask;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import ru.bur.cometogetherandroid.util.AppClient;
import ru.bur.dto.AppUserDto;

public class AuthorizationTask extends AsyncTask<String, Void, String> {
    private String LOG_TAG = "cometogetherandroid ";


    @Override
    protected String doInBackground(String... strings) {
        AppUserDto appUserDto = new AppUserDto();
        appUserDto.setPhoneNumber("1231231239");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String url = AppClient.getUrlByLabel("appUsers");

        String result = restTemplate.postForObject(
                url,
                "{\"phoneNumber\": \"\",\"authorizationToken\": \"\",\"firstName\": \"\",\"lastName\": \"1234\",\"birthdayDate\": null,\"age\": 0}"
                , String.class);

        System.out.println(result);
        return "";
    }
}


