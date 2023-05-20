package com.example.ignite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SemuaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SemuaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<model> mods = new ArrayList<>();
    RecyclerView.Adapter<RenunganAdapter.ViewHolder> adapter = new RenunganAdapter(getContext(), mods);

    public SemuaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SemuaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SemuaFragment newInstance(String param1, String param2) {
        SemuaFragment fragment = new SemuaFragment();
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
        View viuu =  inflater.inflate(R.layout.fragment_semua, container, false);
        FirebaseApp.initializeApp(requireContext());
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView empty = viuu.findViewById(R.id.emptyy);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView recyclerView = viuu.findViewById(R.id.rvsemua);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ArrayList<model> mods = new ArrayList<>();
        RenunganAdapter adapter = new RenunganAdapter(getContext(), mods);
        firebaseDatabase.getReference().child("notes").orderByChild("key")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            model model = dataSnapshot.getValue(com.example.ignite.model.class);
                            assert model != null;
                            model.setKey(dataSnapshot.getKey());
                            mods.add(model);
                        }
                        if (mods.isEmpty()){
                            empty.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }else {
                            empty.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        adapter.setOnItemClickListener(model -> {
                            Intent pindah = new Intent(getContext(), DetailRenungan.class);
                            pindah.putExtra(DetailRenungan.ITEM_EXTRA, model);
                            startActivity(pindah);
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        SearchView searchView;
        searchView = viuu.findViewById(R.id.searchv);
        EditText searchedittext = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        ImageView icon = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
        ImageView silang = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        icon.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
        silang.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
        searchedittext.setTextColor(getResources().getColor(R.color.black));
        searchedittext.setHintTextColor(getResources().getColor(R.color.black));
        searchView.clearFocus();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                newText = newText.toLowerCase();
                ArrayList<model> itemfilter = new ArrayList<>();
                for (model mdl: mods){
                    String title = mdl.getTitle().toLowerCase();
                    if (title.contains(newText)){
                        itemfilter.add(mdl);
                    }
                }
                adapter.setFilter(itemfilter);
                return true;
            }
        });

        return viuu;
    }




}