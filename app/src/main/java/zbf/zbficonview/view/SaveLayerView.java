package zbf.zbficonview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import zbf.zbficonview.R;

/**
 * 保存画布的样例
 * Created by zbf on 2017/6/23.
 */
public class SaveLayerView extends View
{
    private Paint mPaint;
    private Bitmap mBitmap;

    public SaveLayerView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.dog);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,0,0,mPaint);

//        int layerID = canvas.saveLayer(0,0,getWidth(),getHeight(),mPaint,Canvas.ALL_SAVE_FLAG);
        int layerID = canvas.saveLayer(0,0,100,100,mPaint,Canvas.ALL_SAVE_FLAG);
//        canvas.skew(1.327f,0);
//        canvas.drawRect(0,0,150,160,mPaint);
        canvas.drawRect(0,0,500,600,mPaint);
        canvas.restoreToCount(layerID);

    }
}
