package com.example.mtwastewater.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.mtwastewater.Core;
import com.example.mtwastewater.MainActivity;
import com.example.mtwastewater.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ControlFragment extends Fragment {
    private Spinner mSpinType, mSpinOption;
    private Context context;
    private int layout = 0;

    public ControlFragment() {
        // Required empty public constructor
    }
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_control, container, false);
        context = view.getContext();
        mSpinType = view.findViewById(R.id.spintype);
        mSpinOption = view.findViewById(R.id.spinoption);
        layout = R.id.frameLayout;
        return view;
    }

    @Override
    public void onStart() {
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
                        Core.ConfigSpin(context,R.array.sludge, mSpinOption);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
