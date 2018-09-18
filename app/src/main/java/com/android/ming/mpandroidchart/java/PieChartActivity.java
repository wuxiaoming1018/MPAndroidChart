package com.android.ming.mpandroidchart.java;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;

import com.android.ming.mpandroidchart.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity implements View.OnClickListener, OnChartValueSelectedListener {

    private PieChart mPieChart;

    //显示百分比
    private Button btn_show_percentage;
    //显示类型
    private Button btn_show_type;
    //x轴动画
    private Button btn_anim_x;
    //y轴动画
    private Button btn_anim_y;
    //xy轴动画
    private Button btn_anim_xy;
    //保存到sd卡
    private Button btn_save_pic;
    //显示中间文字
    private Button btn_show_center_text;
    //旋转动画
    private Button btn_anim_rotating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechat);
        initView();
//        initData();
    }

    private void initView() {
        btn_show_percentage = (Button) findViewById(R.id.btn_show_percentage);
        btn_show_percentage.setOnClickListener(this);
        btn_show_type = (Button) findViewById(R.id.btn_show_type);
        btn_show_type.setOnClickListener(this);
        btn_anim_x = (Button) findViewById(R.id.btn_anim_x);
        btn_anim_x.setOnClickListener(this);
        btn_anim_y = (Button) findViewById(R.id.btn_anim_y);
        btn_anim_y.setOnClickListener(this);
        btn_anim_xy = (Button) findViewById(R.id.btn_anim_xy);
        btn_anim_xy.setOnClickListener(this);
        btn_save_pic = (Button) findViewById(R.id.btn_save_pic);
        btn_save_pic.setOnClickListener(this);
        btn_show_center_text = (Button) findViewById(R.id.btn_show_center_text);
        btn_show_center_text.setOnClickListener(this);
        btn_anim_rotating = (Button) findViewById(R.id.btn_anim_rotating);
        btn_anim_rotating.setOnClickListener(this);

        initPieChart();
    }

    private void initPieChart() {
        mPieChart = (PieChart) findViewById(R.id.mPieChart);
        //饼状图
        mPieChart.setUsePercentValues(true);//是否使用百分比展示，默认false
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setDragDecelerationFrictionCoef(0.99f);//阻尼系数[0-0.999] 越小，转动越难
        mPieChart.setExtraOffsets(0, 0, 0, 0);

        //饼状图中间圆形属性
        mPieChart.setDrawHoleEnabled(true);//是否绘制饼状图中间的圆
        mPieChart.setHoleColor(Color.WHITE);//饼状图中间的圆的绘制颜色
        mPieChart.setHoleRadius(58f);//饼状图之间的圆的半径大小

        //圆环属性设置
        mPieChart.setTransparentCircleColor(Color.WHITE);//设置圆环颜色
        mPieChart.setTransparentCircleAlpha(110);//设置圆环的透明度【0,255】
        mPieChart.setTransparentCircleRadius(61f);//设置圆环的半径

        //设置中间文件
        mPieChart.setCenterText(generateCenterSpannableText());//设置中间文字
        mPieChart.setCenterTextColor(Color.RED);
        mPieChart.setDrawCenterText(true);//是否绘制中间的文字
        mPieChart.setCenterTextSize(24f);//中间文字字体大小

        //饼状图旋转属性设置
        mPieChart.setRotationAngle(100);//旋转角度
        mPieChart.setRotationEnabled(true);//设置是否可以旋转 默认true
        mPieChart.setHighlightPerTapEnabled(true);//设置旋转时候点中的tab是否高亮 默认true

        //变化监听
        mPieChart.setOnChartValueSelectedListener(this);//点击时候的回调

        //模拟数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(28500f, "一月"));
        entries.add(new PieEntry(26700f, "二月"));
        entries.add(new PieEntry(24000f, "三月"));
        entries.add(new PieEntry(30800f, "四月"));
        entries.add(new PieEntry(24800f, "五月"));
        entries.add(new PieEntry(39100f, "六月"));
        entries.add(new PieEntry(15900f, "七月"));
        entries.add(new PieEntry(45600f, "八月"));
        entries.add(new PieEntry(59200f, "九月"));
        entries.add(new PieEntry(41000f, "十月"));
        entries.add(new PieEntry(49900f, "十一月"));
        entries.add(new PieEntry(59900f, "十二月"));

        //设置数据
        setData(entries);

        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mPieChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);
        l.setDrawInside(false);
        l.setXEntrySpace(0f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // 输入标签样式
        mPieChart.setEntryLabelColor(Color.WHITE);
        mPieChart.setEntryLabelTextSize(12f);
    }

    //设置中间文字
    private SpannableString generateCenterSpannableText() {
        //原文：MPAndroidChart\ndeveloped by Philipp Jahoda
        SpannableString s = new SpannableString("吴小明");
        //s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        //s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        // s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        //s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        // s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        // s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    //设置数据
    private void setData(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "三年级一班");
        dataSet.setSliceSpace(5f);//设置选中的tab离两边的距离
        dataSet.setSelectionShift(8f);//设置选中的tab的多出来的

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
//        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mPieChart.setData(data);
        mPieChart.highlightValues(null);
        //刷新
        mPieChart.invalidate();
    }

    private void initData() {
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(28500f, "一月"));
        entries.add(new PieEntry(26700f, "二月"));
        entries.add(new PieEntry(24000f, "三月"));
        entries.add(new PieEntry(30800f, "四月"));
        entries.add(new PieEntry(24800f, "五月"));
        entries.add(new PieEntry(39100f, "六月"));
        entries.add(new PieEntry(15900f, "七月"));
        entries.add(new PieEntry(45600f, "八月"));
        entries.add(new PieEntry(59200f, "九月"));
        entries.add(new PieEntry(41000f, "十月"));
        entries.add(new PieEntry(49900f, "十一月"));
        entries.add(new PieEntry(59900f, "十二月"));
        PieDataSet dataSet = new PieDataSet(entries, "全年营收情况");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);
        mPieChart.setData(data);
        mPieChart.animateY(3000);
        //设置饼状图距离上下左右的偏移量
        mPieChart.setExtraOffsets(5, 5, 5, 5);
        //设置阻尼系数，【0-1】之间，越小，饼状图转动越困难
        mPieChart.setDragDecelerationFrictionCoef(1f);

        //中间文字属性设置
        mPieChart.setDrawCenterText(true);
        mPieChart.setCenterText("吴小明");
        mPieChart.setCenterTextColor(Color.GRAY);
        mPieChart.setCenterTextSize(18f);

        //饼状图中间圆属性设置
        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);
        mPieChart.setHoleRadius(32f);

        Description description = new Description();
        description.setText("全年消费情况");
        mPieChart.setDescription(description);

        for (IPieDataSet set : mPieChart.getData().getDataSets()) {
            set.setDrawValues(!set.isDrawValuesEnabled());
        }
        mPieChart.invalidate();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //显示百分比
            case R.id.btn_show_percentage:
                for (IDataSet<?> set : mPieChart.getData().getDataSets())
                    set.setDrawValues(!set.isDrawValuesEnabled());

                mPieChart.invalidate();
                break;
            //显示类型
            case R.id.btn_show_type:
                if (mPieChart.isDrawHoleEnabled())
                    mPieChart.setDrawHoleEnabled(false);
                else
                    mPieChart.setDrawHoleEnabled(true);
                mPieChart.invalidate();
                break;
            //x轴动画
            case R.id.btn_anim_x:
                mPieChart.animateX(1400);
                break;
            //y轴动画
            case R.id.btn_anim_y:
                mPieChart.animateY(1400);
                break;
            //xy轴动画
            case R.id.btn_anim_xy:
                mPieChart.animateXY(1400, 1400);
                break;
            //保存到sd卡
            case R.id.btn_save_pic:
                mPieChart.saveToPath("title" + System.currentTimeMillis(), "");
                break;
            //显示中间文字
            case R.id.btn_show_center_text:
                if (mPieChart.isDrawCenterTextEnabled())
                    mPieChart.setDrawCenterText(false);
                else
                    mPieChart.setDrawCenterText(true);
                mPieChart.invalidate();
                break;
            //旋转动画
            case R.id.btn_anim_rotating:
                mPieChart.spin(1000, mPieChart.getRotationAngle(), mPieChart.getRotationAngle() + 360, Easing.EasingOption
                        .EaseInCubic);
                break;
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
