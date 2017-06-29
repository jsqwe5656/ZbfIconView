package zbf.zbficonview.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

/**
 * 带有水波纹的按钮
 * Created by zbf on 2017/6/29.
 */
public class WaveButton extends TextView
{
    private Paint mPaint;
    private RadialGradient mRadialGradient;

    private int mX, mY;
    private int Default_radius = 50;
    private int mCurRadius = 0;

    private ObjectAnimator mAnimator;

    public WaveButton(Context context)
    {
        super(context);
        init();
    }

    public WaveButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public WaveButton(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init()
    {
        mPaint = new Paint();
        setLayerType(LAYER_TYPE_SOFTWARE, null);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //分别获取两个值的mode和数值
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        Log.e("zbf","measureWidthMode:" + measureWidthMode + ",measureHeightMode" + measureHeightMode);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
/*        if (mRadialGradient == null)
        {
            int x = getWidth() / 2;
            int y = getHeight() / 2;
            mRadialGradient = new RadialGradient(x, y, 150, 0x00ffffff, 0xff58faac, Shader.TileMode.CLAMP);
            mPaint.setShader(mRadialGradient);
            canvas.drawCircle(x, y, 150, mPaint);
        }*/
        canvas.drawCircle(mX, mY, mCurRadius, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (mX != event.getX() || mY != mY)
        {
            mX = (int) event.getX();
            mY = (int) event.getY();
            setRadius(Default_radius);
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            return true;
        }
        else if (event.getAction() == MotionEvent.ACTION_UP)
        {
            if (mAnimator != null && mAnimator.isRunning())     //当手指松开的时候取消动画播放
            {
                mAnimator.cancel();
            }
            if (mAnimator == null)
            {
                mAnimator = ObjectAnimator.ofInt(this, "radius", Default_radius, getWidth());
                mAnimator.setDuration(300);
            }
            mAnimator.setInterpolator(new AccelerateInterpolator());
            mAnimator.addListener(new Animator.AnimatorListener()
            {
                @Override
                public void onAnimationStart(Animator animation)
                {
                }

                @Override
                public void onAnimationEnd(Animator animation)
                {
                    //结束的时候让半径为0
                    setRadius(0);
                }

                @Override
                public void onAnimationCancel(Animator animation)
                {

                }

                @Override
                public void onAnimationRepeat(Animator animation)
                {

                }
            });

            mAnimator.start();

        }

        return super.onTouchEvent(event);
    }

    public void setRadius(int radius)
    {
        mCurRadius = radius;
        if (mCurRadius > 0)
        {
            mRadialGradient = new RadialGradient(mX, mY, mCurRadius, 0x00ffffff, 0xff00ffac, Shader.TileMode.CLAMP);
            mPaint.setShader(mRadialGradient);
        }
        postInvalidate();
    }
}
