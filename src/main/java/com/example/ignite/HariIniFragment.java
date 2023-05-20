package com.example.ignite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HariIniFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HariIniFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<model> models;

    public HariIniFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HariIniFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HariIniFragment newInstance(String param1, String param2) {
        HariIniFragment fragment = new HariIniFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_hari_ini, container, false);
        FirebaseApp.initializeApp(requireContext());
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        TextView title, date,content;

        title = view.findViewById(R.id.title);
        content = view.findViewById(R.id.content_today);
        Button ayat;
        ayat = view.findViewById(R.id.ayat_today);


        firebaseDatabase.getReference().child("notes").orderByChild("key").limitToLast(1)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                            model modal = dataSnapshot.getValue(model.class);
                            title.setText(modal.getTitle());
                            content.setText(modal.getContent());
                            ayat.setText("Ayat Hari Ini");
                            ayat.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ngelink(modal.getAyat());
                                }
                            });
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        return view;
    }

    private void ngelink(String ayat) {
        Uri iru = Uri.parse(ayat);
        startActivity(new Intent(Intent.ACTION_VIEW, iru));
    }


}