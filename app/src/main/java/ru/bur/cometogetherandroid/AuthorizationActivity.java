package ru.bur.cometogetherandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
        Intent intent = new Intent(this, ParticipantsActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(1, 1, 1, "Авторизация");
        menu.add(1, 2, 2, "Список встреч");
        menu.add(1, 3, 3, "Создать новую встречу");
        menu.add(1, 4, 4, "Посмотреть встречу");
        menu.add(1, 5, 5, "Посмотреть участников встречи");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case 1:
                intent= new Intent(this, AuthorizationActivity.class);
                startActivity(intent);
                break;
            case 2:
                 intent = new Intent(this, MeetingScrollerActivity.class);
                startActivity(intent);
                break;
            case 3:
               //  intent = new Intent(this, .class);
              //  startActivity(intent);
                break;
            case 4:
             //    intent = new Intent(this, .class);
              //  startActivity(intent);
                break;
            case 5:
                 intent = new Intent(this, ParticipantsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
