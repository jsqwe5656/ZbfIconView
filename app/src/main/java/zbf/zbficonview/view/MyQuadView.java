package zbf.zbficonview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * 绘制贝塞尔曲线
 * Created by zbf on 2017/6/20.
 */
public class MyQuadView extends View
{
    public MyQuadView(Context context)
    {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        //指定起始点
        path.moveTo(100,300);
        //参数中(x1,y1)是控制点坐标，(x2,y2)是终点坐标
        path.quadTo(200,200,300,300);
        path.quadTo(400,400,500,300);

        canvas.drawPath(path,paint);

    }
}
