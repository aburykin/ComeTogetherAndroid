package ru.bur.cometogetherandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

import ru.bur.cometogetherandroid.adapter.PartnerAdapter;
import ru.bur.cometogetherandroid.model.Partner;

public class PartnersActivity extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partners);
        gridView = findViewById(R.id.partnersOfMeeting);


        ArrayList<Partner> partnersTest = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            partnersTest.add(new Partner(String.valueOf(i),R.drawable.ic_launcher_foreground));
        }
        PartnerAdapter partnerAdapter = new PartnerAdapter(this, partnersTest);
        gridView.setAdapter(partnerAdapter);

    }


}
