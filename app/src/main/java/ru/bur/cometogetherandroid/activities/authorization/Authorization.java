package ru.bur.cometogetherandroid.activities.authorization;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.bur.cometogetherandroid.R;
import ru.bur.cometogetherandroid.activities.createMeeting.CreateMeeting;
import ru.bur.cometogetherandroid.activities.meetingScroller.MeetingScroller;
import ru.bur.cometogetherandroid.activities.participants.Participants;
import ru.bur.cometogetherandroid.activities.showMeeting.ShowMeeting;
import ru.bur.cometogetherandroid.activities.userProfile.UserProfile;

public class Authorization extends AppCompatActivity {

    private String LOG_TAG = "Authorization";
    private AutorizationPresender autorizationPresender = new AutorizationPresender();
    private Pattern pattern = Pattern.compile("[0-9]{10}");

    @BindView(R.id.phoneNumber)
    TextView phoneNumber;

    @BindView(R.id.signIn)
    Button signInButton;

    @BindView(R.id.result_auth_tv)
    TextView result_auth_tv;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization);
        ButterKnife.bind(this);

        autorizationPresender.attachView(this);

        signInButton.setOnClickListener(v -> {
            String phoneNumberStr = phoneNumber.getText().toString();
            checkNumber(phoneNumberStr);
            autorizationPresender.tryAuthorization(phoneNumberStr);
        });
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
    public boolean onOptionsItemSelected(MenuItem item) { //TODO это тестовое меню
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

    private void checkNumber(String phoneNumber) {
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            Toast.makeText(Authorization.this, "Номер телефона должен содержать 10 цифр.", Toast.LENGTH_LONG).show();
        }
    }

    public void completeAuthorizationSuccess(String authorizationToken) {
        result_auth_tv.setText(authorizationToken);
        Intent intent = new Intent(this, MeetingScroller.class);
        startActivity(intent);
    }

    public void completeAuthorizationFault(String errorMessage) {
        Toast.makeText(Authorization.this, "Авторизация завершилась с ошибками: " + errorMessage, Toast.LENGTH_LONG).show();
    }

    public void showProgress() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    public void hideProgress() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

}
