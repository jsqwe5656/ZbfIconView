package zbf.zbficonview.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 流布局
 * Created by zbf on 2017/6/30.
 */
public class MyFlowLayout extends ViewGroup
{
    public MyFlowLayout(Context context)
    {
        super(context);
    }

    public MyFlowLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public MyFlowLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p)
    {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams()
    {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int lineWidth = 0;      //记录每一行的宽度
        int lineHeight = 0;     //记录每一行的高度
        int height = 0;         //记录整个布局所占高度
        int width = 0;          //记录整个布局所占宽度
        //得出包含控件个数
        int count = getChildCount();
        for (int i = 0; i < count; i++)
        {
            //循环得到子控件
            View child = getChildAt(i);

            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + marginLayoutParams.rightMargin + marginLayoutParams.leftMargin;
            int childHeight = child.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;

            if (lineWidth + childWidth > measureWidth)
            {
                //需要换行
                width = Math.max(lineWidth, childWidth);
                height += lineHeight;
                //当前控件不足以显示当前控件，所以将扣减调到下一行，所以讲此空间的高度和宽度初始化给lineWidth,lineHeight
                lineHeight = childHeight;
                lineWidth = childWidth;

            }
            else
            {
                //不满足则累加值并去最大值
                lineHeight = Math.max(lineHeight, childHeight);
                lineWidth += childWidth;
            }

            //最后一行不会超出width范围 所以到单独处理
            if (i == count - 1)
            {
                height += lineHeight;
                width = Math.max(width, lineWidth);
            }
            //TODO

        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {

    }
}
