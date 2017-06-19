package zbf.zbficonview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.view.View;

/**
 * 区域绘制
 * Created by zbf on 2017/6/19.
 */

public class MyRegionView extends View
{
    public MyRegionView(Context context)
    {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        //初始化画笔
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);

        //构造一个椭圆路径
        Path ovalPath = new Path();
        RectF rect = new RectF(50, 50, 200, 500);
        ovalPath.addOval(rect, Path.Direction.CCW);
        //setpath时传入一个比椭圆区域小的矩形区域 让其取交集

        Region rgn = new Region();
        rgn.setPath(ovalPath, new Region(50, 50, 200, 200));

        //设置区域
//        Region region = new Region(10, 10, 100, 100);

        //绘制区域
        drawRegion(canvas, paint, rgn);

    }

    /**
     * 绘制区域
     */
    private void drawRegion(Canvas canvas, Paint paint, Region region)
    {
        RegionIterator iter = new RegionIterator(region);
        Rect rect = new Rect();

        while (iter.next(rect))
        {
            canvas.drawRect(rect, paint);
        }

    }


}
