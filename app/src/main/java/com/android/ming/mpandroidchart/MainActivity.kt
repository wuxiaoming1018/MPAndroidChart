package com.android.ming.mpandroidchart

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.ming.mpandroidchart.extension.startActivity
import com.android.ming.mpandroidchart.java.BarChartActivity
import com.android.ming.mpandroidchart.java.LineChartActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mContext: Context;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this
        init()
    }

    private fun init() {
        btn_linechart.setOnClickListener {
            //线性图表
            Intent(mContext, LineChartActivity().javaClass).startActivity(mContext)
        }

        btn_barchart.setOnClickListener {
            //柱状图
            Intent(mContext, BarChartActivity().javaClass).startActivity(mContext)
        }

        btn_candlechart.setOnClickListener {
            //蜡烛图
        }

        btn_combinedchart.setOnClickListener {
            //混合图
        }

        btn_scatterchart.setOnClickListener {
            //散列图
        }

        btn_horizontalchart.setOnClickListener {
            //水平柱状图
        }

        btn_piechart.setOnClickListener {
            //饼图
        }

        btn_radarchart.setOnClickListener {
            //雷达海图
        }
    }
}
