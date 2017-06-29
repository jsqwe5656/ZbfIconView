package zbf.zbficonview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 两种颜色渐变的控件
 * Created by zbf on 2017/6/29.
 */

public class DoubleColorRadiaGradientView extends View
{
    private Paint mPaint;
    private RadialGradient mRadialGradient;     
    private int mRadius = 100;


    public DoubleColorRadiaGradientView(Context context)
    {
        super(context);
        init();
    }

    public DoubleColorRadiaGradientView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public DoubleColorRadiaGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化控件属性
     */
    private void init()
    {
        mPaint = new Paint();
        setLayerType(LAYER_TYPE_SOFTWARE,null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        int[]   colors = new int[]{0xffff0000,0xff00ff00,0xff0000ff,0xffffff00};
        float[] stops  = new float[]{0f,0.2f,0.5f,1f};
        //最后一个参数为填充类型 CLAMP：边缘填充 REPEAT：重复填充 MIRROR：镜像填充
        //多色渐变
        mRadialGradient = new RadialGradient(w/2,h/2,mRadius,colors,stops, Shader.TileMode.REPEAT);
        //双色渐变
//        mRadialGradient = new RadialGradient(w/2,h/2,mRadius,0xffff0000,0xff00ff00, Shader.TileMode.REPEAT);
        mPaint.setShader(mRadialGradient);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

//        canvas.drawCircle(getWidth()/2,getHeight()/2,mRadius,mPaint);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
    }
}
