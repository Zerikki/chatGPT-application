package com.example.myapplication_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bLogin = findViewById(R.id.buttonLogin);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // nous voulons passer a l'activité activity_2
                Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Bienvenue"); //Optional parameters
                startActivity(intent);
            }
        });

    }
}