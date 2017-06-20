package zbf.zbficonview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 使用path的rQuadTo来绘制波浪的视图
 * Created by zbf on 2017/6/20.
 */
public class MyWaveView extends View
{

    public MyWaveView(Context context)
    {
        super(context);
    }

    public MyWaveView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public MyWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    //TODO 
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.moveTo(100, 300);
        /*控制点X坐标=上一个终点X坐标+控制点X位移  = 100+100=200；
        控制点Y坐标  =上一个终点Y坐标+控制点Y位移  = 300-100=200；
        终点X坐标    = 上一个终点X坐标+终点X位移   = 100+200=300；
        终点Y坐标    = 上一个终点Y坐标+控制点Y位移 = 300+0=300;
        所以这句与path.quadTo(200,200,300,300);对等的*/
        path.rQuadTo(100, -100, 200, 0);
        /*控制点X坐标=上一个终点X坐标+控制点X位移  = 300+100=200；
        控制点Y坐标  =上一个终点Y坐标+控制点Y位移  = 300+100=200；
        终点X坐标    = 上一个终点X坐标+终点X位移   = 300+200=500；
        终点Y坐标    = 上一个终点Y坐标+控制点Y位移 = 300+0=300;
        所以这句与path.quadTo(400,400,500,300);对等的*/
        path.rQuadTo(100, 100, 200, 0);

        canvas.drawPath(path,paint);

    }
}
