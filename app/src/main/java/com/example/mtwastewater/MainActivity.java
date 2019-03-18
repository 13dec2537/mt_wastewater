package com.example.mtwastewater;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.mtwastewater.Fragments.BatchFragment;
import com.example.mtwastewater.Fragments.ChemicalFragment;
import com.example.mtwastewater.Fragments.HourlyFragment;
import com.example.mtwastewater.Fragments.SludgeFragment;

public class MainActivity extends AppCompatActivity {
    private Spinner mSpinType, mSpinOption;
    private Context context;
    private int layout = 0;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        context = this;
        mSpinType = findViewById(R.id.spintype);
        mSpinOption = findViewById(R.id.spinoption);
        layout = R.id.frameLayout;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSpinType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSpinOption.setVisibility(View.VISIBLE);
                switch (position){
                    case 0:
                        Core.ReplaceFrag(context, new HourlyFragment(),layout);
                        Core.ConfigSpin(context,R.array.time, mSpinOption);
                        break;
                    case 1:
                        Core.ReplaceFrag(context, new BatchFragment(),layout);
                        Core.ConfigSpin(context,R.array.batch, mSpinOption);
                        break;
                    case 2:
                        Core.ReplaceFrag(context, new ChemicalFragment(),layout);
                        mSpinOption.setVisibility(View.GONE);
                        break;
                        default:
                            Core.ReplaceFrag(context, new SludgeFragment(),layout);
                            Core.ConfigSpin(MainActivity.this,R.array.sludge, mSpinOption);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
    }
}
