package zbf.zbficonview.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * 画笔的基础控件
 * Created by zbf on 2017/6/19.
 */
public class MyCircleView extends View
{
    Context mcontext;

    public MyCircleView(Context context)
    {
        super(context);
        mcontext = context;
    }

    /**
     * 每次重绘的时候自动实现
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        //设置画笔基本属性
        Paint paint = new Paint();
        paint.setAntiAlias(true);                       //抗锯齿
        paint.setColor(Color.RED);                      //画笔颜色
        paint.setStyle(Paint.Style.FILL);               //填充样式
        paint.setStrokeWidth(5);                        //画笔宽度
        paint.setShadowLayer(10, 15, 15, Color.BLUE);   //阴影

        //设置画布背景色
        canvas.drawRGB(255, 255, 255);

        //在画布上使用前面设置的画笔画圆
        canvas.drawCircle(190, 120, 150, paint);

    }
}
