package com.example.mtwastewater;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.mtwastewater.Core;
import com.example.mtwastewater.Fragments.ViewerFragment;

public class MainActivity extends AppCompatActivity {
    public Core core;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Core.ReplaceFrag(this,new ViewerFragment(),R.id.ConstraintMain);
    }
}
