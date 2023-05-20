package com.example.ignite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class galeri_detail extends AppCompatActivity {

    ImageView balik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeri_detail);

        balik = findViewById(R.id.comeback);
        balik.setOnClickListener(v -> startActivity(new Intent(galeri_detail.this, Galeri.class)));
    }
}