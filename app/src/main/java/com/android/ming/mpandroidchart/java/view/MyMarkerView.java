package com.android.ming.mpandroidchart.java.view;

import android.content.Context;
import android.widget.TextView;

import com.android.ming.mpandroidchart.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

public class MyMarkerView extends MarkerView {

    private TextView textView;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public MyMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        textView = (TextView) findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        textView.setText("点击的值:" + e.getY());
        super.refreshContent(e,highlight);
    }

    @Override
    public MPPointF getOffset() {
//        return super.getOffset();
        return new MPPointF(-(getWidth()/2),-(getHeight()/5));
    }
}
