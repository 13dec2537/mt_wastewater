package com.example.mtwastewater.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mtwastewater.Models.Control;
import com.example.mtwastewater.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterControl extends RecyclerView.Adapter<AdapterControl.ViewHolder> {
    private Context context;
    List<Control> list;

    public AdapterControl(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_control,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int i) {
        Control modal = list.get(i);
        Log.d("LOG",modal.getHourly()[0]);
        int count = 0;
        while (count <= modal.getHourly().length){
            count++;
        }
//        if(modal.getHourly().length){
//            viewholder.layoutHeader.setVisibility(View.VISIBLE);
//            viewholder.layoutChild.setVisibility(View.GONE);
//        }else{
//            viewholder.layoutHeader.setVisibility(View.GONE);
//            viewholder.layoutChild.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public void PushContent(List<Control> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTime;
        private ConstraintLayout layoutHeader,layoutChild;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTime = itemView.findViewById(R.id.time);
            layoutChild = itemView.findViewById(R.id.LayoutChild);
            layoutHeader = itemView.findViewById(R.id.LayoutHeader);
        }
    }
}
