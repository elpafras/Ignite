package com.example.ignite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RenunganAdapter extends RecyclerView.Adapter<RenunganAdapter.ViewHolder> implements Filterable{


    Context context;
    ArrayList<model> models;
    ArrayList<model> getModels;

    OnItemClickListener onItemClickListener;



    public RenunganAdapter( Context context, ArrayList<model> models) {

        this.context = context;
        this.models = models;
        getModels = new ArrayList<>(models);
    }

    @NonNull
    @Override
    public RenunganAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recycler, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RenunganAdapter.ViewHolder holder, int position) {
        holder.title.setText(models.get(position).getTitle());
        holder.content.setText(models.get(position).getContent());
        holder.itemView.setOnClickListener(view -> onItemClickListener.OnClick(models.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setFilter(ArrayList<model> searchlist){
        models = new ArrayList<>();
        models.addAll(searchlist);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return udahfilter;
    }

    private Filter udahfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<model> filteredlist = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredlist.addAll(getModels);
            }else{
                String filtered = constraint.toString().toLowerCase().trim();
                for (model ledom : getModels){
                    if (ledom.getTitle().toLowerCase().contains(filtered)){
                        filteredlist.add(ledom);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            models.clear();
            models.addAll((Collection<? extends model>) results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, date, content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.list_item_title);
            content = itemView.findViewById(R.id.list_item_content);


        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void OnClick(model model);
    }

}
