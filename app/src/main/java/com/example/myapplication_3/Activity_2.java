package com.example.myapplication_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import java.net.URI;
import java.net.URISyntaxException;

public class Activity_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Button bStart = findViewById(R.id.start_button);
        TextView test = findViewById(R.id.test);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String value = intent.getStringExtra(MainActivity.EXTRA_MESSAGE); //if it's a string you stored.
        test.setText(value);
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // nous voulons passer a de l'activité 2 au Chat
                Intent intent = new Intent(getApplicationContext(), chat_activity.class);
                startActivity(intent);
            }
        });

    }
}