package ru.bur.cometogetherandroid.task;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import ru.bur.cometogetherandroid.util.AppClient;
import ru.bur.dto.AppUserDto;
import ru.bur.dto.AuthDto;

public class AuthorizationTask extends AsyncTask<String, Void, String> {
    private String LOG_TAG = "cometogetherandroid ";

    @Override
    protected String doInBackground(String... strings) {




      //  AuthDto authDto = new AuthDto();
      //  authDto.setPhoneNumber(strings[0]);
      //  Log.d("LOG_TAG", "authDto="+authDto);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

      //  String test_rest = "{\"phoneNumber\": \"7771243\",\"authorizationToken\": \"2\"}";

       // String result = restTemplate.postForObject(AppClient.getUrlByLabel("appUser"), test_rest ,String.class);
       /* String result = restTemplate.postForObject(
                "http://10.0.2.2:8080/profile/appUsers",
                "{\"phoneNumber\": \"\",\"authorizationToken\": \"\",\"firstName\": \"\",\"lastName\": \"123\",\"birthdayDate\": null,\"age\": 0}"
                ,String.class);*/
        AppUserDto appUserDto = new AppUserDto();
        appUserDto.setPhoneNumber("1231231239");

        // String result = restTemplate.postForObject(AppClient.getUrlByLabel("appUser"), appUserDto ,String.class);
       // String result = restTemplate.postForObject(AppClient.getUrlByLabel("appUser"), "123" ,String.class);
        String result = restTemplate.postForObject("http://10.0.2.2:8080/rest/android", "123" ,String.class);

        System.out.println(result);
        Log.d("LOG_TAG", "result="+result);

        return "XXX";
    }

}


