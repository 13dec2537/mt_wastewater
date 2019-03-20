package com.example.mtwastewater;

import android.content.Context;
import android.content.SharedPreferences;

import static com.example.mtwastewater.Constant.BASE_SHARED;

public class InitSharedPreferences {
    private Context context;

    public InitSharedPreferences(Context context) {
        this.context = context;
    }

    public SharedPreferences initShared(){
        SharedPreferences sh = context.getSharedPreferences(BASE_SHARED,Context.MODE_PRIVATE);
        return sh;
    }
    public SharedPreferences.Editor initEditor(){
        SharedPreferences sh = initShared();
        SharedPreferences.Editor editor = sh.edit();
        return editor;
    }
}
