package com.example.mtwastewater.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mtwastewater.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogCreateFragment extends DialogFragment {
    private static final String KEY_MESSAGE = "Dialog Fragment";
    private static final String KEY_POSITIVE = "key_positive";
    private static final String KEY_NEGATIVE = "key_negative";

    public DialogCreateFragment() {
        // Required empty public constructor
    }

    public static DialogCreateFragment newInstance(String message,String positive,String negative){
        DialogCreateFragment fragment = new DialogCreateFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE,message);
        bundle.putString(KEY_POSITIVE,positive);
        bundle.putString(KEY_NEGATIVE,negative);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_dialog_create, container, false);
        return view;
    }

    public static class Builder {
        private int title;
        private int negative;
        private int positive;
        public Builder() {
        }
        public Builder setTitle(int title){
            this.title = title;
            return this;
        }
        public Builder create(Context context){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            return this;
        }
    }

}
