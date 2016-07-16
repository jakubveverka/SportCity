package com.hackprague.sportcity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class NewEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText etEventSport = (EditText) findViewById(R.id.et_event_sport);
        final EditText etEventTime = (EditText) findViewById(R.id.et_event_time);
        final EditText etEventPlace = (EditText) findViewById(R.id.et_event_place);
        Button btnSave = (Button) findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();

                String key = myRef.child("events").push().getKey();
                Map<String, String> map = new HashMap<>();
                map.put("sport", etEventSport.getText().toString());
                map.put("time", etEventTime.getText().toString());
                map.put("place", etEventPlace.getText().toString());

                myRef.child("events").child(key).setValue(map);
                onBackPressed();
            }
        });

    }

}
