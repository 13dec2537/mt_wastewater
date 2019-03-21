package com.example.mtwastewater;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mtwastewater.Models.Login;
import com.example.mtwastewater.interfaces.Path;
import com.example.mtwastewater.interfaces.init;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mtwastewater.Constant.BASE_SHARED;
import static com.example.mtwastewater.Constant.BASE_URL;
import static com.example.mtwastewater.Constant.UID;

public class Core implements init {
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
        ft.replace(layout,fragment).addToBackStack(null).commit();
    }
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
    public void Login(String user, String pass){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Path configPath = retrofit.create(Path.class);
        Call<Login> call = configPath.CallLogin(user,pass);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.body().isState()){
                    SharedPreferences.Editor editor = initShared();
                    editor.putString(UID,response.body().getId());
                    Log.d("LOG", String.valueOf(response.body().getId()));
                    editor.commit();
                    Intent intent = new Intent(context.getApplicationContext(),MainActivity.class);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable throwable) {
            }
        });
    }

    @Override
    public Path configPath() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Path configPath = retrofit.create(Path.class);
        return configPath;
    }

    @Override
    public SharedPreferences.Editor initShared() {
        SharedPreferences preferences = context.getSharedPreferences(BASE_SHARED,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        return editor;
    }
}
