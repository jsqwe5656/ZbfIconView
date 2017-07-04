package zbf.zbficonview.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import zbf.zbficonview.WaterFallLayoutParams;

/**
 * 实现瀑布流的布局
 * Created by zbf on 2017/7/3.
 */
public class WaterFallLayout extends ViewGroup
{
    private int columns = 6;            //指定列数
    private int hSpace = 20;            //每个图片间的水平间距
    private int vSpace = 20;            //垂直间距
    private int childWidth = 0;         //当前每个图片的宽度
    private int top[];                  //保存当前每列的高度


    public WaterFallLayout(Context context)
    {
        this(context, null);
    }

    public WaterFallLayout(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public WaterFallLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        top = new int[columns];
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new zbf.zbficonview.WaterFallLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p)
    {
        return new WaterFallLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams()
    {
        return new WaterFallLayoutParams(WaterFallLayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        //让每个子控件先测量自己
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //根据总宽度减去总间距得到的就是所有子控件的总宽度和，然后除以列数，就得到了每个item的宽度
        childWidth = (measureWidth - (columns - 1) * hSpace) / columns;

        int wrapWidth;
        int childCount = getChildCount();
        if (childCount < columns)
        {
            wrapWidth = childCount * childWidth + (childCount - 1) * hSpace;
        }
        else
        {
            wrapWidth = measureWidth;
        }
        clearTop();
        for (int i = 0; i < childCount; i++)
        {
            View child = getChildAt(i);
            int childHeight = child.getMeasuredHeight() * childWidth / child.getMeasuredWidth();
            //得带最短列
            int minColum = getMinHeightColum();

            WaterFallLayoutParams mParams = (WaterFallLayoutParams) child.getLayoutParams();
            mParams.left = minColum * (childWidth + hSpace);
            mParams.top = top[minColum];
            mParams.right = mParams.left + childWidth;
            mParams.bottom = mParams.top + childHeight;

            top[minColum] += vSpace + childHeight;
        }
        int wrapHeight;
        wrapHeight = getMaxHeight();
        setMeasuredDimension(measureWidthMode == MeasureSpec.AT_MOST ? wrapWidth : measureWidth, wrapHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int childCount = getChildCount();
        clearTop();
        for (int i = 0; i < childCount; i++)
        {
            View child = getChildAt(i);
            WaterFallLayoutParams mParams = (WaterFallLayoutParams) child.getLayoutParams();

            child.layout(mParams.left, mParams.top, mParams.right, mParams.bottom);
/*            //得到当前要拜访图片的高度
            int childHeight = child.getMeasuredHeight() * childWidth / child.getMeasuredWidth();
            //得到最短列
            int minColum = getMinHeightColum();
            int tleft = minColum * (childWidth + hSpace);
            int ttop = top[minColum];
            int tright = tleft + childWidth;
            int tbottom = ttop + childHeight;
            top[minColum] += vSpace + childHeight;
            child.layout(tleft, ttop, tright, tbottom);*/
        }
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p)
    {
        return p instanceof WaterFallLayoutParams;
    }

    /**
     * 每次计算高度之前清空top[]数组,防止上次数据影响计算
     */
    private void clearTop()
    {
        for (int i = 0; i < columns; i++)
        {
            top[i] = 0;
        }
    }

    /**
     * 得到top[]数组的最短列
     */
    private int getMinHeightColum()
    {
        int minColum = 0;
        for (int i = 0; i < columns; i++)
        {
            if (top[i] < top[minColum])
            {
                minColum = i;
            }
        }
        return minColum;
    }

    /**
     * 得到最长列的高度就是得到整个空间应有的高度
     */
    public int getMaxHeight()
    {
        int maxHeight = 0;
        for (int i = 0; i < columns; i++)
        {
            if (top[i] > maxHeight)
            {
                maxHeight = top[i];
            }
        }
        return maxHeight;
    }

    public interface OnItemClickListener
    {
        void onItemClick(View v, int index);
    }

    /**
     * item点击响应
     */
    public void setOnItemClickListener(final OnItemClickListener listener)
    {
        for (int i = 0; i < getChildCount(); i++)
        {
            final int index = i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    listener.onItemClick(v, index);
                }
            });
        }
    }
}
