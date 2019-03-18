package com.example.mtwastewater;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mtwastewater.Constant.BASE_URL;

public class Core{
    private Context context;

    public Core(Context context) {
        this.context = context;
    }

    public static void ConfigSpin(Context context, int arrayList, Spinner spinner) {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(context.getApplicationContext(),arrayList,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    public static void ReplaceFrag(Context context, Fragment fragment,int layout){
        FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(layout,fragment).commit();
    }
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public void Login(String user, String pass){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ConfigPath configPath = retrofit.create(ConfigPath.class);
        Call<Model> call = configPath.CallLogin(user,pass);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(response.body().isState()){
                    Intent intent = new Intent(context.getApplicationContext(),MainActivity.class);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable throwable) {
            }
        });
    }
}
