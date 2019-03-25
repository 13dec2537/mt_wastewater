package com.example.mtwastewater.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mtwastewater.Adapters.AdapterControl;
import com.example.mtwastewater.Adapters.AdapterViewer;
import com.example.mtwastewater.Core;
import com.example.mtwastewater.Models.Control;
import com.example.mtwastewater.Models.Viewer;
import com.example.mtwastewater.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BatchFragment extends Fragment {
    private Core core;
    private Context context;
    private AdapterControl adapter;
    private RecyclerView recyclerView;
    private List<Control> list = new ArrayList<>();
    private static final String TAG = "BatchFragment";
    public BatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_batch, container, false);
        context = view.getContext();
        core = new Core(context);
        recyclerView = view.findViewById(R.id.RecyclerControl);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        AdapterView();
        Call<List<Control>> call = core.configPath().CallControl("Control");
        call.enqueue(new Callback<List<Control>>() {
            @Override
            public void onResponse(Call<List<Control>> call, Response<List<Control>> response) {
                list = response.body();
                Log.d("LOG", String.valueOf(list));
                adapter.PushContent(response.body());
            }

            @Override
            public void onFailure(Call<List<Control>> call, Throwable throwable) {

            }
        });
    }

    private void AdapterView() {
        adapter = new AdapterControl(context);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
