package ru.bur.cometogetherandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import ru.bur.cometogetherandroid.task.AskServer;

public class AuthorizationActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization);

        Button button = findViewById(R.id.signIn);
        TextView textView = findViewById(R.id.editText);
        button.setOnClickListener(
                (v) -> {
                        new AskServer().execute(textView.getText().toString());
//                    restTemplate.postForLocation(CONNECTION_URL, restTemplate.postForLocation(CONNECTION_URL, textView.getText()));
//                    startActivity(new Intent(this, MeetingScrollerActivity.class));
                }
        );
    }

    //TODO: move it to the meeting on click processor
    public void onClick(View v) {
        Intent intent = new Intent(this, PartnersActivity.class);
        startActivity(intent);
    }

}
