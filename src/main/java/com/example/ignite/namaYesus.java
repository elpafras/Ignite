package com.example.ignite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class namaYesus extends AppCompatActivity {


    ImageView kembali;

    ArrayList<nytb> list = new ArrayList<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    nytbAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nama_yesus);


        kembali = findViewById(R.id.comeback);


        kembali.setOnClickListener(view -> startActivity(new Intent(namaYesus.this, Dashboard.class)));
        FirebaseApp.initializeApp(this);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        RecyclerView recyclerView = findViewById(R.id.recycler_nytb);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        db.getReference().child("nytb").orderByChild("key").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    nytb tb = dataSnapshot.getValue(nytb.class);
                    assert tb != null;
                    tb.setKey(dataSnapshot.getKey());
                    list.add(tb);
                }
                adapter = new nytbAdapter(namaYesus.this, list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                adapter.setnClick(nytb -> {
                    Intent minggat = new Intent(namaYesus.this, detailnytb.class);
                    minggat.putExtra(detailnytb.ITEM_EXTRA, nytb );
                    startActivity(minggat);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        SearchView searchView = findViewById(R.id.searchnytb);
        EditText sredittext = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        ImageView icon = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
        ImageView close = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);

        nytbAdapter adapter = new nytbAdapter(getApplicationContext(), list);

        icon.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
        close.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
        sredittext.setTextColor(getResources().getColor(R.color.black));
        sredittext.setHintTextColor(getResources().getColor(R.color.black));
        searchView.clearFocus();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileList(newText);

                return true;
            }
        });
    }

    private void fileList(String newText) {
        ArrayList<nytb> filtered  = new ArrayList<>();
        for (nytb nytb1 : list){
            if (nytb1.getTitle().toLowerCase().contains(newText.toLowerCase())){
                filtered.add(nytb1);
            }
        }
        if (filtered.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFiltered(filtered);
        }
    }

    public void searchlist(String newText){
        ArrayList<nytb> searchNytb = new ArrayList<>();
        for (nytb nytbb : list){
            String title = nytbb.getTitle().toLowerCase();
            if (title.contains(newText)){
                searchNytb.add(nytbb);
            }
        }
        adapter.searchDataList(searchNytb);
    }



}