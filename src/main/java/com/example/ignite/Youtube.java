package com.example.ignite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Youtube extends AppCompatActivity {

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        back = findViewById(R.id.backbtn);
        back.setOnClickListener(view -> startActivity(new Intent(Youtube.this, Dashboard.class)));

        FirebaseApp.initializeApp(Youtube.this);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        RecyclerView recyclerView = findViewById(R.id.rvyt);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        db.getReference().child("link").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ytm> tms = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()){
                    ytm y = ds.getValue(ytm.class);
                    assert y != null;
                    y.setKey(ds.getKey());
                    tms.add(y);
                }
                YtAdapter adapter = new YtAdapter(Youtube.this, tms);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                adapter.setKlik(new YtAdapter.klik() {
                    @Override
                    public void OnClick(ytm ytm) {
                        Intent move = new Intent(Youtube.this, lihat_yt.class);
                        move.putExtra(lihat_yt.ITEM_EXTRA, (Parcelable) ytm);
                        startActivity(move);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}