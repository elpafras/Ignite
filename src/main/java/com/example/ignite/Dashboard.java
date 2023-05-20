package com.example.ignite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    Button baca, yt, galeri, nytb, out;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);



        baca = findViewById(R.id.btn_baca);
        yt = findViewById(R.id.btn_yt);
        nytb = findViewById(R.id.btn_nytb);
        galeri = findViewById(R.id.btn_galeri);
        out = findViewById(R.id.btn_out);


        baca.setOnClickListener(view -> startActivity(new Intent(Dashboard.this, BacaRenungan.class)));

        yt.setOnClickListener(view -> startActivity(new Intent(Dashboard.this, Youtube.class)));

        nytb.setOnClickListener(view -> startActivity(new Intent(Dashboard.this, namaYesus.class)));

        galeri.setOnClickListener(view -> startActivity(new Intent(Dashboard.this, Galeri.class)));

        out.setOnClickListener(view -> dialogkeluar());
    }

    private void dialogkeluar() {
        View alertcustom = LayoutInflater.from(Dashboard.this).inflate(R.layout.custom_dialog, null);
        AlertDialog.Builder ab = new AlertDialog.Builder(this);

        ab.setView(alertcustom);

        final AlertDialog dialog = ab.create();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();

        Button yes, no;

        yes = alertcustom.findViewById(R.id.yes);
        no = alertcustom.findViewById(R.id.no);

        yes.setOnClickListener(view -> {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });

        no.setOnClickListener(view -> {
            dialog.dismiss();
        });


    }


}