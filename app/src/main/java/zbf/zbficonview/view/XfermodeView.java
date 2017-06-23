package zbf.zbficonview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 使用XFermode来实验 saveLayer()函数
 * Created by zbf on 2017/6/23.
 */
public class XfermodeView extends View
{
    private int width = 400;
    private int height = 400;
    private Bitmap dstBmp;
    private Bitmap srcBmp;
    private Paint mPaint;

    public XfermodeView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        srcBmp = makeBmp(width, height, 0);
        dstBmp = makeBmp(width, height, 1);
        mPaint = new Paint();
    }


    //创建一个矩形的bitmap

    /**
     * 画图
     *
     * @param width  宽
     * @param height 搞
     * @param mode   0是矩形 1是圆
     *
     * @return 画好的bitmap
     */
    private Bitmap makeBmp(int width, int height, int mode)
    {
        Bitmap bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas can = new Canvas(bm);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        if (mode == 0)
        {
            paint.setColor(0xFF66AAFF);
            can.drawRect(0, 0, width, height, paint);
        }
        else if (mode == 1)
        {
            paint.setColor(0xFFFFCC44);
            can.drawOval(new RectF(0, 0, width, height), paint);
        }

        return bm;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);

        int layerID = canvas.saveLayer(0, 0, width * 2, height * 2, mPaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(dstBmp, 0, 0, mPaint);
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(srcBmp, width / 2, height / 2, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerID);


    }
}
