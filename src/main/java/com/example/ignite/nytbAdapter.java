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

public class nytbAdapter extends RecyclerView.Adapter<nytbAdapter.ViewHolder> implements Filterable {

    Context context;
    ArrayList<nytb> list;

    ArrayList<nytb> namaYesus;

    nClick nClick;

    public void setFiltered(ArrayList<nytb> filtered){
        this.list = filtered;
        notifyDataSetChanged();
    }

    public nytbAdapter(Context context, ArrayList<nytb> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public nytbAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_nytb, parent,false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull nytbAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.bait.setText("Bait  :  " + list.get(position).getBait());
        holder.nada.setText(list.get(position).getNada());
        holder.koor.setText("Koor :  " + list.get(position).getKoor());
        holder.nolagu.setText(list.get(position).getNo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nClick.ncliku(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void searchDataList(ArrayList<nytb> searchlist){
        list = searchlist;
        notifyDataSetChanged();
    }

//    public void setFilter(ArrayList<nytb> filter){
//        list = new ArrayList<>();
//        list.addAll(filter);
//        notifyDataSetChanged();
//    }

    @Override
    public Filter getFilter() {
        return filtered;
    }

    public Filter filtered = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<nytb> filteredlist = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredlist.addAll(namaYesus);
            }else{
                String fil = constraint.toString().toLowerCase().trim();
                for (nytb btyn : namaYesus){
                    if (btyn.getTitle().toLowerCase().contains(fil)){
                        filteredlist.add(btyn);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((Collection<? extends nytb>) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, bait, koor, nada, nolagu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.list_item_title);
            bait = itemView.findViewById(R.id.list_item_bait);
            nada = itemView.findViewById(R.id.list_item_nada);
            koor = itemView.findViewById(R.id.list_item_koor);
            nolagu = itemView.findViewById(R.id.list_item_number);
        }
    }

    public interface nClick{
        void ncliku(nytb nytb);
    }

    public void setnClick(nytbAdapter.nClick nClick) {
        this.nClick = nClick;
    }
}
