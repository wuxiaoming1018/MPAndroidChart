package com.android.ming.mpandroidchart.extension

import android.content.Context
import android.content.Intent

fun Intent.startActivity(context: Context){
    context.startActivity(this)
}