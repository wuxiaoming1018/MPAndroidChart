package com.android.ming.mpandroidchart.java;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BarChartActivity extends AppCompatActivity {

    private BarChart mChat;
    private String[] mValues = new String[]{"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
    private List<String> mGroup = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChat = new BarChart(this);
        setContentView(mChat);
//        intiView();
        initGroup();
    }

    private void initGroup() {
//        Float[] group1 = new Float[]{12f, 18f, 9f, 23f, 25f, 41f};
//        Float[] group2 = new Float[]{10f, 19f, 22f, 15f, 32f, 39f};
        List<BarEntry> entriesGroup1 = new ArrayList<>();
        List<BarEntry> entriesGroup2 = new ArrayList<>();
        List<BarEntry> entriesGroup3 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            entriesGroup1.add(new BarEntry(i, new Random().nextInt(20) + 20));
            entriesGroup2.add(new BarEntry(i, new Random().nextInt(20) + 20));
            //堆积柱状图
            entriesGroup3.add(new BarEntry(i, new float[]{new Random().nextInt(5) + 10,new Random().nextInt(10) + 5,new Random().nextInt(9) + 12}));
            mGroup.add("第" + (i + 1) + "季度");
        }
        BarDataSet set1 = new BarDataSet(entriesGroup1, "group1");
        set1.setColor(Color.RED);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        BarDataSet set2 = new BarDataSet(entriesGroup2, "group2");
        set2.setColor(Color.GREEN);
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        BarDataSet set3 = new BarDataSet(entriesGroup3, "group3");
        set3.setColors(ColorTemplate.COLORFUL_COLORS);
        set3.setAxisDependency(YAxis.AxisDependency.LEFT);
        BarData data = new BarData(set1, set2, set3);
        data.setBarWidth(0.2f);
        mChat.setData(data);
        mChat.groupBars(-0.6f, 0.3f, 0.05f);
        mChat.invalidate();
        Legend legend = mChat.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);//靠右
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);//垂直排列
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//靠上
        Description description = new Description();
        description.setText("公司前半年财务报表(单位：万元)");
        mChat.setDescription(description);
        XAxis xAxis = mChat.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mGroup.get((int) value);
            }
        });
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
                Log.i("110", axis.toString());
                return mValues[(int) value];
            }
        });
    }
}
