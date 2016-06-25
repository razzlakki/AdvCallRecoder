package dms.com.automaticcallrecordmaster.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import dms.com.automaticcallrecordmaster.R;

/**
 * Created by rpeela on 12/9/15.
 */
public class CircleView extends View {

    private int padding;
    private Paint circlePaint;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        padding = (int) getResources().getDimension(R.dimen.circle_padding);
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(getResources().getColor(R.color.primary));
    }

    public void setCircleColor(int color) {
        circlePaint.setColor(color);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - padding, circlePaint);
    }
}
