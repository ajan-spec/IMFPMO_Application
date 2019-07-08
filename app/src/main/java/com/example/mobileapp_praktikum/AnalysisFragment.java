package com.example.mobileapp_praktikum;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnalysisFragment extends Fragment implements AbsListView.OnScrollListener{


    public AnalysisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Objects.requireNonNull(getActivity()).setTitle("Analyse");
        View view = inflater.inflate(R.layout.fragment_analysis, container, false);
        BottomNavigationView bottomNav = view.findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_analysis);
        ((MainActivity) getActivity()).FragmentListener(bottomNav);

        AnalyseMonatListAdapter adapter = new AnalyseMonatListAdapter(getActivity(),((MainActivity)getActivity()).getErgebnisse(), getActivity().getSupportFragmentManager());
        ListView monatAnalyseergebnistListView  =(ListView) view.findViewById(R.id.listview);

        monatAnalyseergebnistListView.setAdapter(adapter);
        monatAnalyseergebnistListView.setOnScrollListener(this);
        //must happen only once. maybe check if service already running
        Context context = getActivity();
        context.startService(new Intent(context, LocationUpdatesService.class));
        return view;
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        int lastItem = i +i1;

        if(((MainActivity) getActivity()).getErgebnisse().size() == lastItem){
            ((MainActivity) getActivity()).getMehrAnalyseErgebnisse(5);
            absListView.invalidateViews();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }
}
