package com.android.ming.mpandroidchart

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*
import kotlin.collections.ArrayList

class LineChartActivity : AppCompatActivity() {

    private var chat: LineChart? = null
    private var xValus: ArrayList<String>? = ArrayList()
    private var yValus: ArrayList<Entry>? = ArrayList()
    private var dataSet: LineDataSet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chat = LineChart(this)
        setContentView(chat)
        initData()
    }

    private fun initData() {
        for (i in 0..11) {
            var profix = Random().nextFloat()
            xValus!!.add((i + 1).toString() + "月")
            yValus!!.add(Entry(profix.toFloat(), i))
        }
        dataSet = LineDataSet(yValus,"公司年度利润")
        dataSet!!.setColors(ColorTemplate.COLORFUL_COLORS)
        with(chat!!){
            data = LineData(xValus,dataSet)
            setDescription("公司年度利润")
            animateY(3000)
//            setScaleEnabled(false)//禁止使用X、Y轴缩放
            isDoubleTapToZoomEnabled = false //禁止双击放大功能
            isScaleXEnabled = false//禁止X轴缩放
            isScaleYEnabled=true //允许Y轴缩放
        }
    }
}
