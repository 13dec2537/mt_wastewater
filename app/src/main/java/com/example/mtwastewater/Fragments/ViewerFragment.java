package com.example.mtwastewater.Fragments;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.mtwastewater.Adapters.AdapterViewer;
import com.example.mtwastewater.ConfigPath;
import com.example.mtwastewater.Models.Viewer;
import com.example.mtwastewater.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mtwastewater.Constant.BASE_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewerFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private List<Viewer> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterViewer adapter;
    private FloatingActionButton floatCreate;
    private DatePickerDialog datePickerDialog;
    private Calendar c;

    public ViewerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewer, container, false);
        recyclerView = view.findViewById(R.id.RecyclerView);
        floatCreate = view.findViewById(R.id.FloatCreate);
        floatCreate.setOnClickListener(this);
        context = view.getContext();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        AdapterView();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ConfigPath configPath = retrofit.create(ConfigPath.class);
        Call<List<Viewer>> call = configPath.CallViewer();
        call.enqueue(new Callback<List<Viewer>>() {
            @Override
            public void onResponse(Call<List<Viewer>> call, Response<List<Viewer>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    adapter.PushContent(list);
                    Log.d("LOG", String.valueOf(list.size()));
                }
            }

            @Override
            public void onFailure(Call<List<Viewer>> call, Throwable throwable) {
                Log.d("LOG","Viewer onFailure" + throwable);
            }
        });
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
                final Dialog dialog = new Dialog(context);
                dialog.setTitle(R.string.titledialog);
                dialog.setContentView(R.layout.fragment_dialog_create);
                TextView textView = dialog.findViewById(R.id.btnDatePicker);
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
                                Log.d("LOG", String.valueOf(day));
                            }
                        },day,month,year);

                        datePickerDialog.show();
                    }

                });
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.80);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.50);
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                break;
        }
    }
}
