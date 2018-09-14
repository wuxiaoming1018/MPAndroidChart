package com.android.ming.mpandroidchart

import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*
import kotlin.collections.ArrayList

class BarChartActivity : AppCompatActivity() {

    private var chart: BarChart? = null
    var entries = ArrayList<BarEntry>()
    var xVaule = ArrayList<String>()
    private var dataSet: BarDataSet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chart = BarChart(this)
        setContentView(chart)
        initData()
    }

    private fun initData() {
        for (a in 0..11) {
            entries.add(BarEntry(Random().nextFloat() * 1000, a))
            xVaule.add((a + 1).toString() + "月")
        }
        dataSet = BarDataSet(entries, "吴小明")
        dataSet!!.setColors(ColorTemplate.COLORFUL_COLORS)
        show()
        chart!!.animateY(3000)
//        for (a in 0..10) {
        add()
//        }
    }

    fun show() {
        with(chart!!) {
            data = BarData(xVaule, dataSet)
//            animateY(3000)
            setDescription("柱状图展示效果")
            isHighlightEnabled = true
        }
    }

    fun add() {
        Thread {
            SystemClock.sleep(3000)
            for (i in 0..11) {
                xVaule.add("d")
                entries.add(BarEntry(Random().nextFloat()*1000, 12+i))
            }
//            show()
//            xVaule.add("date")
//            var index = 0
//            entries.add(BarEntry(555.0f, entries.size))
//            xVaule.add("date")
//            index = entries.size
//            entries.add(BarEntry(345.0f, index))
//            xVaule.add("date")
//            index = entries.size
//            entries.add(BarEntry(856.0f, index))
//            xVaule.add("date")
//            index = entries.size
//            entries.add(BarEntry(489.0f, index))
            chart!!.notifyDataSetChanged()
        }.start()
    }

}
