package com.example.mtwastewater.Fragments.sub;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.example.mtwastewater.Constant.*;
import com.example.mtwastewater.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Hourly_tab_1 extends Fragment{
    private EditText ed_dy,ed_gs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hourly_tab_1, container, false);
        ed_dy = view.findViewById(R.id.ed_dy);
        ed_gs = view.findViewById(R.id.ed_gs);
        ed_dy.addTextChangedListener(new CustomTextWatcher(ed_dy));
        ed_gs.addTextChangedListener(new CustomTextWatcher(ed_gs));
        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    private class CustomTextWatcher implements TextWatcher {
        private EditText e;
        public CustomTextWatcher(EditText ed) {
            this.e = ed;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            ContentValues cv = new ContentValues();
            JSONObject json = new JSONObject();
            try {
                json.put("name",e.getTag());
                json.put("value",e.getText());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            cv.put(_STATIC.COL_DATA, String.valueOf(json));
            Log.d("LOG", String.valueOf(cv));
        }
    }

    public void Update(){

    }
}
