package ru.bur.cometogetherandroid.activities.userProfile;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.bur.cometogetherandroid.R;

public class UserProfile extends AppCompatActivity {

    TextInputEditText userName;
    TextInputEditText userPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        userName = findViewById(R.id.userName);
        userPhone = findViewById(R.id.userPhone);

    }
}
