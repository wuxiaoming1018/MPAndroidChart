package com.android.ming.mpandroidchart.java;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LineChartActivity extends AppCompatActivity {

    private LineChart mChat;
    private String[] mVaule = new String[]{"Q1", "Q2", "Q3", "Q4"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChat = new LineChart(this);
        setContentView(mChat);
        initView();
    }

    private void initView() {
        List<Entry> company1 = new ArrayList<>();
        List<Entry> company2 = new ArrayList<>();
        Entry c1e1 = new Entry(0f, 100000f); // 0 == quarter 1
        company1.add(c1e1);
        Entry c1e2 = new Entry(1f, 140000f); // 1 == quarter 2 ...
        company1.add(c1e2);
        Entry c1e3 = new Entry(2f, 120000f); // 1 == quarter 2 ...
        company1.add(c1e3);
        Entry c1e4 = new Entry(3f, 140000f); // 1 == quarter 2 ...
        company1.add(c1e4);
        // and so on ...

        Entry c2e1 = new Entry(0f, 130000f); // 0 == quarter 1
        company2.add(c2e1);
        Entry c2e2 = new Entry(1f, 115000f); // 1 == quarter 2 ...
        company2.add(c2e2);
        Entry c2e3 = new Entry(2f, 90000f); // 1 == quarter 2 ...
        company2.add(c2e3);
        Entry c2e4 = new Entry(3f, 160000f); // 1 == quarter 2 ...
        company2.add(c2e4);

        LineDataSet dataSet1 = new LineDataSet(company1, "Company1");
        dataSet1.setColor(Color.RED);
        dataSet1.setAxisDependency(AxisDependency.RIGHT);
        LineDataSet dataSet2 = new LineDataSet(company2, "Company2");
        dataSet2.setColor(Color.YELLOW);
        dataSet2.setAxisDependency(AxisDependency.RIGHT);

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet1);
        dataSets.add(dataSet2);
        LineData data = new LineData(dataSets);
        mChat.setData(data);
        mChat.animateY(3000);
        mChat.invalidate();
        XAxis xAxis = mChat.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mVaule[(int)value];
            }
        });
    }


}
