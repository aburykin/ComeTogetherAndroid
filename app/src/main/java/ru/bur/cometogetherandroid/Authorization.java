package ru.bur.cometogetherandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.bur.cometogetherandroid.task.AskServer;
import ru.bur.cometogetherandroid.task.AuthorizationTask;

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
            // проверяю данные
            String phoneNumberStr = phoneNumber.getText().toString();
            AuthorizationTask authorizationTask = new AuthorizationTask();
            authorizationTask.execute(phoneNumberStr);

            if (checkNumber(phoneNumberStr)) {
                // AskServer authorizationTask = new AskServer();
            } else {
                //TODO нужно дать пользователю понять, что он ошибся.
                Log.e(LOG_TAG, "phoneNumberStr=" + phoneNumberStr);
            }
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
