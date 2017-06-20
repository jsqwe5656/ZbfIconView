package zbf.zbficonview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 使用path的rQuadTo来绘制波浪的视图
 * Created by zbf on 2017/6/20.
 */
public class MyWaveView extends View
{
    private Paint mPaint;
    private Path mPath;
    //波形长度
    private int mItemWaveLength = 400;
    //X，Y轴位移
    private int dx,dy;

    public MyWaveView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        //填充
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        dy = 0;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
//        testDraw(canvas);
        //每次绘制的时候重置
        mPath.reset();
        int originY = 300;
        int halfWaveLen = mItemWaveLength/2;
        //起始位置向左移动一个波形长度
        mPath.moveTo(-mItemWaveLength + dx,originY);
//        mPath.moveTo(-mItemWaveLength + dx,originY + dy);
        dy +=1;
        //循环画出当前屏幕容得下的所有波形
        for (int i = -mItemWaveLength;
             i<=getWidth()+mItemWaveLength;
             i+=mItemWaveLength)
        {
            //画出波长中的前半个波形
            mPath.rQuadTo(halfWaveLen/2 + 10,-50,halfWaveLen + 15,0);
            //后半个
            mPath.rQuadTo(halfWaveLen/2 + 20,50,halfWaveLen + 25,0);
        }

        mPath.lineTo(getWidth(),getHeight());
        mPath.lineTo(0,getHeight());
        mPath.close();

        canvas.drawPath(mPath,mPaint);


    }

    /**
     * 开始动画（让他动起来）
     */
    public void startAnim()
    {
        ValueAnimator anim = ValueAnimator.ofInt(0,mItemWaveLength);
        //动画完成时间
        anim.setDuration(500);
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.setInterpolator(new LinearInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        anim.start();

    }

    /**
     * 使用path的rQuadTo函数去画波形
     * @param canvas
     */
    private void testDraw(Canvas canvas)
    {
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
        //这个函数的唯一坐标都是以上一个重点位置胃基准来做偏移的
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
