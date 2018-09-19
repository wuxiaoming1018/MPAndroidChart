package com.android.ming.mpandroidchart.java;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class RadarChartActivity extends AppCompatActivity {

    private RadarChart mChart;
    private String[] mParties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I"
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChart = new RadarChart(this);
        setContentView(mChart);
        initView();
    }

    private void initView() {
        mChart.setWebLineWidth(1.5f);//线条宽度，圆形向外辐射的线条
        mChart.setWebLineWidthInner(1.0f);//内部线条宽度,外面的环状线条
        mChart.setWebAlpha(100);//所有线条WebLine透明度

        setData();
        //X坐标属性设置
        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(12f);

        //Y坐标属性设置
        YAxis yAxis = mChart.getYAxis();
        yAxis.setLabelCount(6, false);//标签个数
        yAxis.setTextSize(12f);
        yAxis.setStartAtZero(true);//是否从0开始

        //图例属性设置
        Legend legend = mChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setXEntrySpace(2f);//图例X间距
        legend.setYEntrySpace(1f);//图例Y间距

        Description description = new Description();
        description.setText("雷达海图描述");
        mChart.setDescription(description);
    }

    private void setData() {
        float mult = 150;
        int cnt = 9;
        List<RadarEntry> yVals1 = new ArrayList<>();
        List<RadarEntry> yVals2 = new ArrayList<>();
        List<String> xVals = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            yVals1.add(new RadarEntry((float) (Math.random() * mult) + mult / 2, i));
            yVals2.add(new RadarEntry((float) (Math.random() * mult) + mult / 2, i));
            xVals.add(mParties[i % mParties.length]);
        }

        RadarDataSet set1 = new RadarDataSet(yVals1,"company1");
        set1.setColor(Color.RED);
        //是否实心填充区域
        set1.setDrawFilled(true);
        //数据线条宽度
        set1.setLineWidth(2f);

        RadarDataSet set2 = new RadarDataSet(yVals2,"company2");
        set2.setColor(Color.GREEN);
        //是否实心填充区域
        set2.setDrawFilled(true);
        //数据线条宽度
        set2.setLineWidth(2f);

//        List<RadarDataSet> dataSets = new ArrayList<>();
//        dataSets.add(set1);
//        dataSets.add(set2);
        RadarData data = new RadarData(set1,set2);
        data.setValueTextSize(8f);
        data.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return mParties[(int) value % mParties.length];
            }
        });
        mChart.setData(data);
        mChart.invalidate();
    }
}
