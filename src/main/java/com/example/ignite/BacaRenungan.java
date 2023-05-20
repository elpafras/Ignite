package com.example.ignite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class BacaRenungan extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    ImageView back;

    Toolbar tb;

    ArrayList<model> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baca_renungan);

        loadFragment(new HariIniFragment());

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(ColorStateList.valueOf(Color.WHITE));
        navigationView.setItemTextColor(ColorStateList.valueOf(Color.WHITE));



        tb =  findViewById(R.id.toolbar);

        back = findViewById(R.id.kembali);
        back.setOnClickListener(view -> startActivity(new Intent(BacaRenungan.this, Dashboard.class)));

//        FirebaseApp.initializeApp(BacaRenungan.this);
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        TextView empty = findViewById(R.id.textView2);
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(lm);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        database.getReference().child("notes").orderByChild("key").limitToLast(1).addValueEventListener(new ValueEventListener() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ArrayList<model> models = new ArrayList<>();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    model m = dataSnapshot.getValue(model.class);
//                    Objects.requireNonNull(m).setKey(dataSnapshot.getKey());
//                    models.add(m);
//
//                }
//
//                if (models.isEmpty()){
//                    empty.setVisibility(View.VISIBLE);
//                    recyclerView.setVisibility(View.GONE);
//                }else{
//                    empty.setVisibility(View.GONE);
//                    recyclerView.setVisibility(View.VISIBLE);
//                }
//
//                RenunganAdapter adapter = new RenunganAdapter(BacaRenungan.this, models);
//                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//
//                adapter.setOnItemClickListener(model -> {
//                    Intent move = new Intent(BacaRenungan.this, DetailRenungan.class);
//                    move.putExtra(DetailRenungan.ITEM_EXTRA, model);
//                    startActivity(move);
//                });
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.hari_ini:
                fragment = new HariIniFragment();
                break;

            case R.id.all:
                fragment = new SemuaFragment();
                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null ){
            getSupportFragmentManager().beginTransaction().replace(R.id.fcontainer, fragment).commit();
            return true;
        }
        return false;
    }
}