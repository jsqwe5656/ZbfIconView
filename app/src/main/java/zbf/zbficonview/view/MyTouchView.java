package zbf.zbficonview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 触摸界面
 * Created by zbf on 2017/6/20.
 */
public class MyTouchView extends View
{
    Path mpath = new Path();
    float mPreX,mPreY;

    public MyTouchView(Context context)
    {
        super(context);
    }

    public MyTouchView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                //手指触摸点当做起点
                mPreX = event.getX();
                mPreY = event.getY();
                mpath.moveTo(mPreX, mPreY);

                //表示已经消费这次事件
                return true;

            case MotionEvent.ACTION_MOVE:
                //使用lineTo画出来的曲线不够圆滑
//                mpath.lineTo(event.getX(), event.getY());
                //使用贝塞尔函数来画
                float endX = (mPreX + event.getX())/2;
                float endY = (mPreY + event.getY())/2;
                mpath.quadTo(mPreX,mPreY,endX,endY);
                mPreX = event.getX();
                mPreY = event.getY();
                //重绘
                postInvalidate();
                break;

        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);

        canvas.drawPath(mpath, paint);

    }

    /**
     * 重置
     */
    public void reset()
    {
        mpath.reset();
        invalidate();
    }


}
