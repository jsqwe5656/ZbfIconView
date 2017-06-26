package zbf.zbficonview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 退回图形栈的demo
 * Created by zbf on 2017/6/26.
 */
public class RestoreToCountView extends View
{
    private Paint mPaint;
    private String TAG = "zbf";

    public RestoreToCountView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

//        test1(canvas);
        test2(canvas);

    }

    /**
     * 测试restoreToCount（）函数
     */
    private void test1(Canvas canvas)
    {
        int id1 = canvas.save();
        canvas.clipRect(0,0,800,800);
        canvas.drawColor(Color.RED);
        Log.d(TAG,"count:"+canvas.getSaveCount()+"  id1:"+id1);

        int id2 = canvas.saveLayer(0,0,getWidth(),getHeight(),mPaint,Canvas.ALL_SAVE_FLAG);
        canvas.clipRect(100,100,700,700);
        canvas.drawColor(Color.GREEN);
        Log.d(TAG,"count:"+canvas.getSaveCount()+"  id2:"+id2);

        int id3 = canvas.saveLayerAlpha(0,0,getWidth(),getHeight(),0xf0,Canvas.ALL_SAVE_FLAG);
        canvas.clipRect(200,200,600,600);
        canvas.drawColor(Color.YELLOW);
        Log.d(TAG,"count:"+canvas.getSaveCount()+"  id3:"+id3);

        int id4 = canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.clipRect(300,300,500,500);
        canvas.drawColor(Color.BLUE);
        Log.d(TAG,"count:"+canvas.getSaveCount()+"  id4:"+id4);

        //退回到id3之前的状态
        canvas.restoreToCount(id3);
        canvas.drawColor(Color.GRAY);
        Log.d(TAG,"count:" + canvas.getSaveCount());
    }

    /**
     * 测试restore()与restoreToCount(int count)关系
     * restore的意义是把回退栈中的最上层画布状态出栈，恢复画布状态。
     * restoreToCount(int count)的意义是一直退栈，直到指定层count做为栈顶，将此之前的所有动作都恢复。
     * 无论哪种save方法，哪个FLAG标识，保存画布时都使用的是同一个栈
     * restore()与restoreToCount(int count)针对的都是同一个栈，所以是完全可以通用和混用的
     */
    private void test2(Canvas canvas)
    {
        canvas.save();
        Log.d(TAG,"count:"+canvas.getSaveCount());
        canvas.save(Canvas.ALL_SAVE_FLAG);
        Log.d(TAG,"count:"+canvas.getSaveCount());
        canvas.saveLayer(0,0,getWidth(),getHeight(),mPaint,Canvas.CLIP_SAVE_FLAG);
        Log.d(TAG,"count:"+canvas.getSaveCount());
        canvas.saveLayer(0,0,getWidth(),getHeight(),mPaint,Canvas.MATRIX_SAVE_FLAG);
        Log.d(TAG,"count:"+canvas.getSaveCount());
        canvas.saveLayer(0,0,getWidth(),getHeight(),mPaint,Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        Log.d(TAG,"count:"+canvas.getSaveCount());
        canvas.saveLayer(0,0,getWidth(),getHeight(),mPaint,Canvas.ALL_SAVE_FLAG);
        Log.d(TAG,"count:"+canvas.getSaveCount());

    }


}
