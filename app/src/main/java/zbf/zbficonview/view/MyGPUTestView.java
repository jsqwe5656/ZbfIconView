package zbf.zbficonview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import zbf.zbficonview.R;

/**
 * 实现一些ps的功能的样例
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
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //进制硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);


    }




}
