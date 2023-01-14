package com.example.safely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CallorMessage extends AppCompatActivity {
    Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callor_message);

        close = findViewById(R.id.close);
        close.setOnClickListener(view -> {
            startActivity(new Intent(CallorMessage.this,sosActivity.class));
        });

    }
}