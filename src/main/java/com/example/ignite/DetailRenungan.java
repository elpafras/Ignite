package com.example.ignite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailRenungan extends AppCompatActivity {

    public static final String ITEM_EXTRA = "item_extra";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_renungan);



        TextView title_detail, date_detail, content_detail, tglrenungan;
        Button ayat;
        ImageView back;

        title_detail = findViewById(R.id.title_detail);
        ayat = findViewById(R.id.ayat);
        content_detail = findViewById(R.id.content_detail);
        tglrenungan = findViewById(R.id.tgl_renungan);
        back = findViewById(R.id.backbtn);

        back.setOnClickListener(view -> startActivity(new Intent(DetailRenungan.this, BacaRenungan.class)));


        model model = (model) getIntent().getParcelableExtra(ITEM_EXTRA);

        tglrenungan.setText("Renungan " + model.getTitle());

        if (model!=null){
            title_detail.setText(model.getTitle());
            ayat.setText("Ayat " + model.getTitle());

            ayat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    url(model.getAyat());
                }
            });

            content_detail.setText(model.getContent());
        }
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }



    }

    private void url(String ayat) {
        Uri uri = Uri.parse(ayat);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}