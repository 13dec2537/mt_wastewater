package com.example.mtwastewater.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mtwastewater.Models.Control;
import com.example.mtwastewater.R;

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
        if(list.get(i).getType().equals("header")){
            viewholder.layoutHeader.setVisibility(View.VISIBLE);
            viewholder.layoutChild.setVisibility(View.GONE);
            viewholder.header.setText(list.get(i).getValue());
        }else{
            viewholder.layoutHeader.setVisibility(View.GONE);
            viewholder.layoutChild.setVisibility(View.VISIBLE);
            viewholder.row.setText(list.get(i).getValue());
        }

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
        private TextView row;
        private TextView header;
        private ConstraintLayout layoutHeader;
        private LinearLayout layoutChild;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
            row = itemView.findViewById(R.id.row);
            layoutChild = itemView.findViewById(R.id.LayoutChild);
            layoutHeader = itemView.findViewById(R.id.LayoutHeader);
        }
    }
}
