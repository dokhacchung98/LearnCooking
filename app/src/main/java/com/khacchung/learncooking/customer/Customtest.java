package com.khacchung.learncooking.customer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 29/08/2017.
 */

public class Customtest extends com.github.ksoichiro.android.observablescrollview.ObservableGridView {
    public Customtest(Context context) {
        super(context);
    }

    public Customtest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Customtest(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = View.MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }
}
