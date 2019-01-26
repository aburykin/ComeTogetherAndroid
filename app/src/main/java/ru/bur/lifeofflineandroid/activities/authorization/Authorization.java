package ru.bur.lifeofflineandroid.activities.authorization;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.bur.lifeofflineandroid.LifeOfflineApp;
import ru.bur.lifeofflineandroid.R;
import ru.bur.lifeofflineandroid.activities.meetingScroller.MeetingScroller;

public class Authorization extends AppCompatActivity {

    private String LOG_TAG = "Authorization";

    @Inject
    public AutorizationPresender autorizationPresender;
    private Pattern pattern = Pattern.compile("[0-9]{10}");

    @BindView(R.id.phoneNumber)
    TextView phoneNumber;

    @BindView(R.id.signIn)
    Button signInButton;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization);
        ButterKnife.bind(this);

        ((LifeOfflineApp) getApplicationContext()).getAppComponent().inject(this); // внедрение зависимостей происходит вручную


        autorizationPresender.attachView(this);

        signInButton.setOnClickListener(v -> {
            String phoneNumberStr = phoneNumber.getText().toString();
            if (isCorrectNumber(phoneNumberStr)) {
                autorizationPresender.tryAuthorization(phoneNumberStr);
            }
        });

        if (autorizationPresender.isUserAuthrizated()) {
            Intent intent = new Intent(this, MeetingScroller.class);
            startActivity(intent);
        }
    }

    private boolean isCorrectNumber(String phoneNumber) {
        Matcher matcher = pattern.matcher(phoneNumber);
        boolean isCorrect = matcher.matches();
        if (!isCorrect) {
            Toast.makeText(Authorization.this, "Номер телефона должен содержать 10 цифр.", Toast.LENGTH_LONG).show();
        }
        return isCorrect;
    }

    public void completeAuthorizationSuccess(String authorizationToken) {
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
