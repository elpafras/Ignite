package com.example.ignite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DriveAdapter extends RecyclerView.Adapter<DriveAdapter.ViewHolder> {

    Context context;

    ArrayList<drivem> dm;

    clicked clicked;


    public DriveAdapter(Context context, ArrayList<drivem> dm) {
        this.context = context;
        this.dm = dm;
    }

    @NonNull
    @Override
    public DriveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_galeri, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DriveAdapter.ViewHolder holder, int position) {
        holder.title.setText(dm.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked.OnClick(dm.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dm.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_galeri);
        }

    }

    public void setClicked(clicked clicked) {
        this.clicked = clicked;
    }

    public interface clicked{
        void OnClick(drivem drivem);
    }
}
