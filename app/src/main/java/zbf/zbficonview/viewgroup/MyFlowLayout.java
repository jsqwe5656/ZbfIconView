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
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
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
            //计算每个子空间的宽和高
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

            } else
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
            //当属性是MeasureSpec.EXACTLY时，那么它的高度就是确定的，
            // 只有当是wrap_content时，根据内部控件的大小来确定它的大小时，大小是不确定的，属性是AT_MOST,此时，就需要我们自己计算它的应当的大小，并设置进去
            setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth
                    : width, (measureHeightMode == MeasureSpec.EXACTLY) ? measureHeight
                    : height);

        }

    }

    /**
     * 由于控件要后移和换行，所以我们要标记当前控件的left坐标和top坐标
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int count = getChildCount();
        int lineWidth = 0;      //累加当前行的行款
        int lineHeight = 0;     //当前行的行高
        int top = 0, left = 0;   //当前坐标的top坐标和left坐标
        //计算每个空间的top和left左边后调用lauout()来布局每个子空间
        for (int i = 0; i < count; i++)
        {
            View child = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            //计算出当前子控件的宽高
            int childWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

            if (childWidth + lineWidth > getMeasuredWidth())
            {
                //如果需要换行，当前控件将跑到下一行，从最左边开始，
                //所以left就是0，而top则需要加上上一行的行高，才是这个控件的top点;
                top += lineHeight;
                left = 0;
                //重新初始化行高跟宽
                lineHeight = childHeight;
                lineWidth = childWidth;
            } else
            {
                //累加行宽高，取最大值
                lineHeight = Math.max(lineHeight, childHeight);
                lineWidth += childWidth;
            }
            //计算childView的left,top,right,bottom
            int lc = left + layoutParams.leftMargin;    //左坐标+左边距是控件的开始位置
            int tc = top + layoutParams.topMargin;      //同理得出顶点位置
            int rc = lc + child.getMeasuredWidth();
            int bc = tc + child.getMeasuredHeight();
            child.layout(lc, tc, rc, bc);
            //将left设置为下一个子空间的起始点
            left += childWidth;

        }

    }
}
