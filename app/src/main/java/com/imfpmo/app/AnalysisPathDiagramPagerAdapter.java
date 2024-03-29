package com.imfpmo.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.github.mikephil.charting.charts.PieChart;

public class AnalysisPathDiagramPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Path path;


    public AnalysisPathDiagramPagerAdapter(Context context, Path path){
        this.context = context;
        this.layoutInflater = (LayoutInflater)this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
        this.path = path;
    }

    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = this.layoutInflater.inflate(R.layout.analyse_diagram_pie_chart, container, false);
        PieChart pieChart = view.findViewById(R.id.diagramm);
        switch (position){
            case 0: AnalysisDiagramMaker.makeEmissionPieChart(path, pieChart, context); break;
            case 1: AnalysisDiagramMaker.makeDistancePieChart(path,pieChart,context); break;
            case 2: AnalysisDiagramMaker.makeTimeEffortPieChart(path,pieChart,context);break;
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
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
