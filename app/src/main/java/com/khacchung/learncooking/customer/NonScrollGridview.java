package com.khacchung.learncooking.customer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

/**
 * Created by Administrator on 24/08/2017.
 */

public class NonScrollGridview extends GridView {

    public NonScrollGridview(Context context) {
        super(context);
    }

    public NonScrollGridview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonScrollGridview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#000000"));
        paint.setTextSize(50);
        if (getCount() == 0) {
            canvas.drawText("Không có dữ liệu...", canvas.getWidth() / 4, canvas.getHeight() / 2, paint);
        }
    }
}