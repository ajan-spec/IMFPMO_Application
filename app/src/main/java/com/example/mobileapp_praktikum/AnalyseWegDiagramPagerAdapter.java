package com.example.mobileapp_praktikum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;

public class AnalyseWegDiagramPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private AnalyseergebnisWeg weg;


    public AnalyseWegDiagramPagerAdapter(Context context, AnalyseergebnisWeg weg){
        this.context = context;
        this.layoutInflater = (LayoutInflater)this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
        this.weg = weg;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        View view = this.layoutInflater.inflate(R.layout.analyse_diagram_pie_chart, container, false);
        PieChart pieChart = (PieChart) view.findViewById(R.id.diagramm);
        switch (position){
            case 0: AnalyseDiagramMaker.makeWegGesamtCO2PieChart(weg , pieChart); break;
            case 1: AnalyseDiagramMaker.makeWegGesamtDistanzPieChart(weg ,pieChart); break;
            case 2: AnalyseDiagramMaker.makeWegGesamtZeitPieChart(weg ,pieChart);break;
        }
        container.addView(view);
        return view;
    }


    public int getCount() {
        return 3;
    }

    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}