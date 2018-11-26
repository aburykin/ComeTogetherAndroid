package ru.bur.cometogetherandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.bur.cometogetherandroid.rest.AppUserRestClient;
import ru.bur.dto.AuthDto;

public class Authorization extends AppCompatActivity {

    private String LOG_TAG = "cometogetherandroid ";

    TextView phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization);
        phoneNumber = findViewById(R.id.phoneNumber);

        Button button = findViewById(R.id.signIn);
        button.setOnClickListener(v -> {
            String phoneNumberStr = phoneNumber.getText().toString();
            Log.e(LOG_TAG, "phoneNumberStr=" + phoneNumberStr);

            AuthDto authDto = new AuthDto();
            authDto.setPhoneNumber(phoneNumberStr);
            AppUserRestClient.authorization(this, authDto);

            System.out.println();
            // тут нужно получить результат запроса
        });
    }

    private boolean checkNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("[0-9]{10}");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "Авторизация");
        menu.add(1, 2, 2, "Список встреч");
        menu.add(1, 3, 3, "Создать новую встречу");
        menu.add(1, 4, 4, "Посмотреть встречу");
        menu.add(1, 5, 5, "Посмотреть участников встречи");
        menu.add(1, 6, 6, "Профиль пользователя");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case 1:
                intent = new Intent(this, Authorization.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, MeetingScroller.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, CreateMeeting.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(this, ShowMeeting.class);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(this, Participants.class);
                startActivity(intent);
            case 6:
                intent = new Intent(this, UserProfile.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

 /*
            // проверяю данные


            AuthorizationTask authorizationTask = new AuthorizationTask();
            authorizationTask.execute(phoneNumberStr);

            if (checkNumber(phoneNumberStr)) {
                // AskServer authorizationTask = new AskServer();
            } else {
                //TODO нужно дать пользователю понять, что он ошибся.

            }
            */