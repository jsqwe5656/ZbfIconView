package zbf.zbficonview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * 画笔的demo
 * Created by zbf on 2017/6/19.
 */

public class MyPaintViewDemo extends View
{
    public MyPaintViewDemo(Context context)
    {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        //画线
        drawLine(canvas, paint);
        //绘制路径
        drawPath(canvas, paint);


    }

    /**
     * 绘制路径
     */
    private void drawPath(Canvas canvas, Paint paint)
    {
        Path path = new Path();
        path.moveTo(10, 10);             //设置起点
        path.lineTo(10, 100);            //第一条线的终点也是第二条线的起点
        path.lineTo(300, 100);           //第二条线
        path.lineTo(500, 100);           //第三条线
        path.close();

        canvas.drawPath(path, paint);
    }

    /**
     * 画线
     */
    private void drawLine(Canvas canvas, Paint paint)
    {
        //一条线
        canvas.drawLine(100, 100, 200, 200, paint);
        float[] pts = {10, 10, 100, 100, 200, 200, 400, 400};
        //多条线
        canvas.drawLines(pts, paint);
    }


}
