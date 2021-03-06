package zbf.zbficonview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import zbf.zbficonview.R;

/**
 * 仿QQ拖动的小红点
 * Created by zbf on 2017/6/27.
 */
public class RedPointView extends FrameLayout
{
    //与point几乎一致 用于坐标不是整数的情况
    private PointF mStartPoint, mCurPoint;
    //圆的半径
    private float DEFAULT_RADIUS = 30;
    private float mRadius = DEFAULT_RADIUS;

    private Paint mPaint;
    private Path mPath;

    private boolean mTouch = false;
    private boolean isAnimStart = false;

    private TextView mTipTextView;
    private ImageView mImageView;


    public RedPointView(@NonNull Context context)
    {
        super(context);
        viewInit();
    }

    public RedPointView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        viewInit();
    }

    public RedPointView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        viewInit();
    }

    /**
     * 初始化
     */
    private void viewInit()
    {
        mStartPoint = new PointF(100, 100);
        mCurPoint = new PointF();

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);

        mPath = new Path();
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        textViewInit(params);
        imageViewInit(params);

    }

    /**
     * 初始化爆炸效果动画容器
     *
     * @param params
     */
    private void imageViewInit(LayoutParams params)
    {
        mImageView = new ImageView(getContext());
        mImageView.setLayoutParams(params);
        mImageView.setImageResource(R.drawable.tip_anim);
        mImageView.setVisibility(INVISIBLE);
        addView(mImageView);
    }

    /**
     * 初始化文字控件
     *
     * @param params
     */
    private void textViewInit(LayoutParams params)
    {
        mTipTextView = new TextView(getContext());
        mTipTextView.setLayoutParams(params);
        mTipTextView.setPadding(10, 10, 10, 10);
        mTipTextView.setTextColor(Color.BLACK);
        mTipTextView.setBackgroundResource(R.drawable.tv_bg);
        mTipTextView.setText("99+");
        addView(mTipTextView);
    }

    //绘制子视图
    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), mPaint, Canvas.ALL_SAVE_FLAG);

        if (!mTouch || isAnimStart)
        {
            mTipTextView.setX(mStartPoint.x - mTipTextView.getWidth() / 2);
            mTipTextView.setY(mStartPoint.y - mTipTextView.getHeight() / 2);
        }
        else
        {
            calculatePath();
            //初始圆的位置
            canvas.drawCircle(mStartPoint.x, mStartPoint.y, mRadius, mPaint);
            //跟随手指移动圆的位置
            canvas.drawCircle(mCurPoint.x, mCurPoint.y, mRadius, mPaint);
            canvas.drawPath(mPath, mPaint);
            mTipTextView.setX(mCurPoint.x - mTipTextView.getWidth() / 2);
            mTipTextView.setY(mCurPoint.y - mTipTextView.getHeight() / 2);
        }
        canvas.restore();
        super.dispatchDraw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                Rect rect = rectInit();
                //判断触摸点是否在初始点上
                if (rect.contains((int) (event.getRawX()), (int) (event.getRawY())))
                    mTouch = true;
                break;
            case MotionEvent.ACTION_UP:
                mTouch = false;
                break;
        }
        mCurPoint.set(event.getX(), event.getY());
        postInvalidate();
        return true;
    }

    /**
     * 计算并绘制曲线
     */
    private void calculatePath()
    {
        float x = mCurPoint.x;
        float y = mCurPoint.y;
        float startX = mStartPoint.x;
        float startY = mStartPoint.y;
        //根据角度算出四边形的四个点
        float dx = x - startX;
        float dy = y - startY;
        //角度
        double a = Math.atan(dy / dx);
        float offsetX = (float) (mRadius * Math.sin(a));
        float offsetY = (float) (mRadius * Math.cos(a));

        //根据角度算出四边形的四个点
        float x1 = startX - offsetX;
        float y1 = startY + offsetY;

        float x2 = x - offsetX;
        float y2 = y + offsetY;

        float x3 = x + offsetX;
        float y3 = y - offsetY;

        float x4 = startX + offsetX;
        float y4 = startY - offsetY;

        float anchorX = (startX + x) / 2;
        float anchorY = (startY + y) / 2;

        float distance = (float) Math.sqrt(Math.pow(y - startY, 2) + Math.pow(x - startX, 2));
        mRadius = DEFAULT_RADIUS - distance / 15;
        if (mRadius < 9)
        {
            isAnimStart = true;
            //取控件中间点
            mImageView.setX(mCurPoint.x - mTipTextView.getWidth() / 2);
            mImageView.setY(mCurPoint.y - mTipTextView.getHeight() / 2);
            mImageView.setVisibility(VISIBLE);
            ((AnimationDrawable) mImageView.getDrawable()).start();

            mTipTextView.setVisibility(GONE);
        }

        //重置
        mPath.reset();
        mPath.moveTo(x1, y1);
        mPath.quadTo(anchorX, anchorY, x2, y2);
        mPath.lineTo(x3, y3);
        mPath.quadTo(anchorX, anchorY, x4, y4);
        mPath.lineTo(x1, y1);


    }

    /**
     * 初始化初始圆点所在的矩阵位置
     */
    private Rect rectInit()
    {
        Rect rect = new Rect();
        int[] location = new int[2];
        //获取当前控件所在屏幕的位置
        mTipTextView.getLocationOnScreen(location);
        rect.left = location[0];
        rect.top = location[1];
        rect.right = mTipTextView.getWidth() + location[0];
        rect.bottom = mTipTextView.getHeight() + location[1];
        return rect;
    }
}
