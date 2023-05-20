package com.example.ignite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class YtAdapter extends RecyclerView.Adapter<YtAdapter.ViewHolder> {



    Context context;

    ArrayList<ytm> ytms;

    klik klik;

    public YtAdapter(Context context, ArrayList<ytm> ytms) {
        this.context = context;
        this.ytms = ytms;
    }


    @NonNull
    @Override
    public YtAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_youtube, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull YtAdapter.ViewHolder holder, int position) {
        holder.title.setText(ytms.get(position).getTitle());
        Glide.with(context).load(ytms.get(position).getImage())
                        .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                klik.OnClick(ytms.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return ytms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.judulyt);
            imageView = itemView.findViewById(R.id.gambar);
        }
    }

    public void setKlik(YtAdapter.klik klik) {
        this.klik = klik;
    }

    public interface klik{
        void OnClick(ytm ytm);
    }
}
