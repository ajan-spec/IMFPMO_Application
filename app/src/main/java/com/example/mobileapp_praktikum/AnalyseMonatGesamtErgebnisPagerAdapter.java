package com.example.mobileapp_praktikum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AnalyseMonatGesamtErgebnisPagerAdapter extends PagerAdapter{
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<AnalyseergebnisMonat> monate;


    public AnalyseMonatGesamtErgebnisPagerAdapter(Context context, ArrayList<AnalyseergebnisMonat> monate){
        this.context = context;
        this.layoutInflater = (LayoutInflater)this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
        this.monate = monate;
    }

    public Object instantiateItem(ViewGroup container, int position)  {
        View view = this.layoutInflater.inflate(R.layout.analyse_diagram_bar_chart, container, false);
        BarChart barChart = (BarChart) view.findViewById(R.id.diagramm);
        switch (position){
            case 0: AnalyseDiagramMaker.makeMonatGesamtCO2BarChart(monate, barChart); break;
            case 1: AnalyseDiagramMaker.makeMonatGesamtDistanzBarChart(monate,barChart); break;
            case 2: AnalyseDiagramMaker.makeMonatGesamtZeitBarChart(monate ,barChart);break;
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}