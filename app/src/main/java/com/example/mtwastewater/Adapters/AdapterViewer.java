package com.example.mtwastewater.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mtwastewater.Models.Viewer;
import com.example.mtwastewater.R;

import java.util.List;

public class AdapterViewer extends RecyclerView.Adapter<AdapterViewer.ViewHolder> {
    private List<Viewer> list;
    private Context context;

    public AdapterViewer(Context context, List<Viewer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtId.setText(String.valueOf(list.get(i).getWaste_id()));
    }

    @Override
    public int getItemCount() {
        if(list != null) { return list.size(); }
        return list.size();
    }

    public void PushContent(List<Viewer> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
        }
    }
}
