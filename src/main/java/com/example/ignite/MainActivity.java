package com.example.ignite;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Handler h = new Handler();
        h.postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
            finish();
        }, 3000L);
    }
}