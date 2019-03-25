package com.example.mtwastewater.Fragments;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.mtwastewater.Adapters.AdapterViewer;
import com.example.mtwastewater.Core;
import com.example.mtwastewater.InitSharedPreferences;
import com.example.mtwastewater.Models.Viewer;
import com.example.mtwastewater.Models.WasteWater;
import com.example.mtwastewater.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mtwastewater.Constant.UID;

public class ViewerFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private List<Viewer> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterViewer adapter;
    private FloatingActionButton floatCreate;
    private DatePickerDialog datePickerDialog;
    private InitSharedPreferences initSh;
    private SharedPreferences sh;
    private BottomNavigationView bottomNavigationView;
    private Calendar c;
    private Core core;
    public ViewerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewer, container, false);
        recyclerView = view.findViewById(R.id.RecyclerView);
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        floatCreate = view.findViewById(R.id.FloatCreate);
        floatCreate.setOnClickListener(this);
        context = view.getContext();
        core = new Core(context);
        initSh = new InitSharedPreferences(context);
        sh = initSh.initShared();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Call<List<Viewer>> call = core.configPath().CallViewer();
        call.enqueue(new Callback<List<Viewer>>() {
            @Override
            public void onResponse(Call<List<Viewer>> call, Response<List<Viewer>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    adapter.PushContent(list);
                }
            }

            @Override
            public void onFailure(Call<List<Viewer>> call, Throwable throwable) {
                Log.d("LOG","Viewer onFailure" + throwable);
            }
        });
        AdapterView();
    }

    private void AdapterView() {
        adapter = new AdapterViewer(context,list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.FloatCreate:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.fragment_dialog_create,null);
                TextView textView = view.findViewById(R.id.btnDatePicker);
                InitSharedPreferences initSh = new InitSharedPreferences(context);
                SharedPreferences sh = initSh.initShared();
                c = Calendar.getInstance();
                textView.setText(String.valueOf(c.get(Calendar.DAY_OF_MONTH)) + "/" + String.valueOf(c.get(Calendar.MONTH))
                        + "/" + String.valueOf(c.get(Calendar.YEAR)));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int day = c.get(Calendar.DAY_OF_MONTH);
                        int month = c.get(Calendar.MONTH);
                        int year = c.get(Calendar.YEAR);
                        datePickerDialog = new DatePickerDialog(context,new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                textView.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                            }
                        },day,month,year);
                        datePickerDialog.updateDate(year,month,day);
                        datePickerDialog.show();
                    }

                });
                builder.setView(view);
                builder.setTitle(R.string.title_create);
                builder.setIcon(R.drawable.baseline_create_white_18dp);
                builder.setPositiveButton(R.string.positivecreate, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Call<WasteWater> call = core.configPath().CallCreate("createAndroid",sh.getString(UID,""));
                        call.enqueue(new Callback<WasteWater>() {
                            @Override
                            public void onResponse(Call<WasteWater> call, Response<WasteWater> response) {
                                if(response.isSuccessful()){

                                }
                            }

                            @Override
                            public void onFailure(Call<WasteWater> call, Throwable throwable) {

                            }
                        });
                    }
                });
                builder.setNegativeButton(R.string.negativetext, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                break;
        }
    }
}
