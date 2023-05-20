package com.example.ignite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class detailnytb extends AppCompatActivity {

    public static final String ITEM_EXTRA = "item_extra";

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailnytb);

        ScrollView scrollView = findViewById(R.id.scrollviuu);
        RelativeLayout relativeLayout = findViewById(R.id.relatel);
        TextView title_detail, nada_detail, bait_detail, koor_detail, tbjd, nosong, leotk;
        ImageView back;

        scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));

        back = findViewById(R.id.kembalipulang);
        back.setOnClickListener(view -> startActivity(new Intent(detailnytb.this, namaYesus.class)));

        title_detail = findViewById(R.id.title_detail);
        nada_detail = findViewById(R.id.nada_detail);
        bait_detail = findViewById(R.id.bait_detail);
        koor_detail = findViewById(R.id.koor_detail);
        tbjd = findViewById(R.id.tbjd);
        nosong = findViewById(R.id.nolagu);
        leotk = findViewById(R.id.leotk);

        nytb Nytb = (nytb) getIntent().getParcelableExtra(ITEM_EXTRA);

        tbjd.setText("Nama Yesus Terus Bersuara");
        nosong.setText(Nytb.getNo());
        title_detail.setText(Nytb.getTitle());
        nada_detail.setText(Nytb.getNada());
        bait_detail.setText(Nytb.getBait());
        koor_detail.setText(Nytb.getKoor());

        if (koor_detail.toString().equals("")){
            koor_detail.setVisibility(View.GONE);
            leotk.setVisibility(View.GONE);
        }


    }
}