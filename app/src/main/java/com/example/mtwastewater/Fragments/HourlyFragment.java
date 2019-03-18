package com.example.mtwastewater.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mtwastewater.Core;
import com.example.mtwastewater.Fragments.sub.Hourly_tab_1;
import com.example.mtwastewater.Fragments.sub.Hourly_tab_2;
import com.example.mtwastewater.R;

public class HourlyFragment extends Fragment {
    private TabLayout tabLayout;
    private Context context;
    private int page = 0;
    public HourlyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_hourly, container, false);
        tabLayout = view.findViewById(R.id.tabs);
        context = view.getContext();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        Core.ReplaceFrag(context,new Hourly_tab_1(),R.id.ConstrainHourly);
                        break;
                    case 1:
                        Core.ReplaceFrag(context,new Hourly_tab_2(),R.id.ConstrainHourly);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tabLayout.getTabAt(1).select();
        tabLayout.getTabAt(page).select();
    }

    @Override
    public void onStop() {
        super.onStop();
        page = tabLayout.getSelectedTabPosition();
    }
}
