package ru.bur.cometogetherandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AuthorizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization);

        Button button = findViewById(R.id.signIn);
        button.setOnClickListener(
                (v) -> startActivity(new Intent(this, MeetingScrollerActivity.class))
        );

    }

    //TODO: move it to the meeting on click processor
    public void onClick(View v) {
        Intent intent = new Intent(this, PartnersActivity.class);
        startActivity(intent);
    }

}
