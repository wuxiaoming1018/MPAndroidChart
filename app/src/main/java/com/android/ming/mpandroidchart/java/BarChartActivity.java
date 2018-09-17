package com.android.ming.mpandroidchart.java;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class BarChartActivity extends AppCompatActivity {

    private BarChart mChat;
    private String[] mValues = new String[]{"一月", "二月", "三月", "四月", "五月", "六月", "七月"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChat = new BarChart(this);
        setContentView(mChat);
//        intiView();
        initGroup();
    }

    private void initGroup() {
        Float[] group1 = new Float[]{12f, 18f, 9f, 23f, 25f, 41f};
        Float[] group2 = new Float[]{10f, 19f, 22f, 15f, 32f, 39f};
        List<BarEntry> entriesGroup1 = new ArrayList<>();
        List<BarEntry> entriesGroup2 = new ArrayList<>();
        for (int i = 0; i < group1.length; i++) {
            entriesGroup1.add(new BarEntry(i, group1[i]));
            entriesGroup2.add(new BarEntry(i, group2[i]));
        }
        BarDataSet set1 = new BarDataSet(entriesGroup1, "group1");
        set1.setColor(Color.RED);
        BarDataSet set2 = new BarDataSet(entriesGroup2, "group2");
        set2.setColor(Color.GREEN);
        BarData data = new BarData(set1, set2);
        data.setBarWidth(0.45f);
        mChat.setData(data);
        mChat.groupBars(1980f, 0.06f, 0.02f);
        mChat.invalidate();
        Description description = new Description();
        description.setText("公司前半年财务报表(单位：万元)");
        mChat.setDescription(description);
    }

    private void intiView() {
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 88f));
        entries.add(new BarEntry(2f, 66f));
        entries.add(new BarEntry(3f, 56f));
        entries.add(new BarEntry(5f, 18f));
        entries.add(new BarEntry(6f, 63f));

        BarDataSet dataSet = new BarDataSet(entries, "柱状图描述");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(dataSet);
        dataSet.setBarBorderWidth(1f);
        mChat.setData(data);
        mChat.setFitBars(true);
//        mChat.invalidate();
        mChat.animateY(3000);
        Description description = new Description();
        description.setText("公司前半年财务报表(单位：万元)");
        mChat.setDescription(description);
        XAxis xAxis = mChat.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return null;
            }
        });
    }
}
