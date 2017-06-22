package zbf.zbficonview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Canvas画布实验控件
 * Created by zbf on 2017/6/22.
 */
public class MyCanvasTestView extends View
{
    private Bitmap mBitmap;
    private Paint mPaint;
    private Canvas mBmpCanvas;

    public MyCanvasTestView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mBitmap = Bitmap.createBitmap(500,500,Bitmap.Config.ARGB_8888);
        mBmpCanvas = new Canvas(mBitmap);

    }

    /**
     * 绘制视图本身的方法
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

    }

    /**
     * 绘制子视图
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);

    }
}
