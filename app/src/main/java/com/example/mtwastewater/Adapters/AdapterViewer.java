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

import com.example.mtwastewater.Core;
import com.example.mtwastewater.Fragments.BatchFragment;
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
        viewHolder.txtId.setText("รายการที่ : " + String.valueOf(list.get(i).getWaste_id()));
        viewHolder.txtDate.setText("วันที่สร้าง : " + String.valueOf(list.get(i).getWaste_date()));
        viewHolder.txtCreate.setText("ผู้สร้าง : " + String.valueOf(list.get(i).getName()));
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Core.ReplaceFrag(context,new BatchFragment(),R.id.ConstraintMain);
            }
        });
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
        private TextView txtDate;
        private TextView txtCreate;
        private ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            layout = itemView.findViewById(R.id.LayoutViewer);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtCreate = itemView.findViewById(R.id.txtCreate);
        }
    }
}
