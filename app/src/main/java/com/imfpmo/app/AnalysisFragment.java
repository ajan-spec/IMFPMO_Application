package com.imfpmo.app;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnalysisFragment extends Fragment implements AbsListView.OnScrollListener{
    private int preLast;
    private AnalysisMonthListAdapter adapter;
    private final static int firstLoadCount = 2;
    private final static int nextLoadCount = 2;
    private final static String NO_DATA_ALERT_TEXT = "Es liegen zurzeit keine Analyseergebnisse vor.";
    private final static String CONNECTION_ERROR_ALERT_TEXT = "Es trafen Probleme beim Laden der Analyseergebnisse vor.";

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
        int errorCode = AnalysisLoader.getInstance().loadFirst(firstLoadCount);
        if(errorCode == 1) showInformationDialog(NO_DATA_ALERT_TEXT);
        else if(errorCode == 2) showInformationDialog(CONNECTION_ERROR_ALERT_TEXT);

        adapter = new AnalysisMonthListAdapter(getActivity(),AnalysisLoader.getInstance().getResults(), getActivity().getSupportFragmentManager());
        ListView monatAnalyseergebnistListView  = view.findViewById(R.id.listviewMonth);
        ((DrawerLocker) getActivity()).setDrawerLocked(false);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).show();

        monatAnalyseergebnistListView.setAdapter(adapter);
       monatAnalyseergebnistListView.setOnScrollListener(this);
        return view;
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        switch (absListView.getId()){
            case R.id.listviewMonth:
                final int lastItem = i + i1;
                if(lastItem == i2){
                    if(preLast != lastItem){
                        preLast = lastItem;
                        AnalysisLoader.getInstance().loadResults(nextLoadCount);

                        adapter.notifyDataSetChanged();
                    }

                }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    public void showInformationDialog(String text){
        AlertDialog.Builder a_builder = new AlertDialog.Builder(this.getActivity());
        a_builder.setMessage(text)
                .setCancelable(false)
                .setNegativeButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }) ;
        AlertDialog alert = a_builder.create();
        alert.setTitle("Information");
        alert.show();
    }
}
