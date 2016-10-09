package com.mallock.messiiitd.fragments;

import android.icu.text.UnicodeSet;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mallock.messiiitd.R;

import java.util.ArrayList;

/**
 * Created by Mallock on 06-10-2016.
 */

public class StatsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getContext())
                .inflate(R.layout.stats_layout, container, false);
        //entries
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(4f,0));
        entries.add(new Entry(8f, 1));
        entries.add(new Entry(6f, 2));
        entries.add(new Entry(12f, 3));
        entries.add(new Entry(18f, 4));
        entries.add(new Entry(9f, 5));

        //dataset
        PieDataSet dataset = new PieDataSet(entries, "# of Calls");
        dataset.setColors(ColorTemplate.LIBERTY_COLORS);

        //labels
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");

        PieChart chart = new PieChart(getContext());
        getActivity().setContentView(chart);
        chart.setData(new PieData(labels, dataset));
        chart.setDescription("# of times Alice called Bob");
        chart.animateX(2000);

        return view;
    }

}
