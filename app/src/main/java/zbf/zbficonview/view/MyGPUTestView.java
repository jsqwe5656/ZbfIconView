package zbf.zbficonview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import zbf.zbficonview.R;

/**
 * 实现一些ps的功能的样例（大于15api已不支持部分方法 略过）
 * Created by zbf on 2017/6/21.
 */
public class MyGPUTestView extends View
{
    private Paint mPaint;
    private Bitmap mBitmap;

    public MyGPUTestView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.dog);
        //禁止硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);


    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        int width = 300;
        int height = width * mBitmap.getHeight()/mBitmap.getWidth();

        int layID = canvas.saveLayer(0,0,width,height,mPaint,Canvas.ALL_SAVE_FLAG);

        //画一个小狗的bitmap
        canvas.drawBitmap(mBitmap,null,new Rect(0,0,width,height),mPaint);
        //找指定颜色的（这里是指定的白色）选区，容差为100 参考ps的魔棒
//        mPaint.setXfermode(new AvoidXfermode(Color.WHITE,100,AvoidXfermode.Mode.TARGET));
//        mPaint.setXfermode(new PorterDuffXfermode(Color.WHITE,100,AvoidXfermode.Mode.TARGET));
        canvas.drawRect(0,0,width,height,mPaint);


        canvas.restoreToCount(layID);
    }
}
