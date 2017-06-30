package zbf.zbficonview.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义的容器
 * Created by zbf on 2017/6/30.
 */
public class MyLineLayout extends ViewGroup
{
   public MyLineLayout(Context context)
    {
        super(context);
    }

    public MyLineLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public MyLineLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    /**
     * 从指定的XML中获取对应的宽、高值
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * 如果使用默认的构造，就生成layout_width="wrap_content"、layout_height="wrap_content"对应的参数
     */
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
    }

    /**
     * 测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        //wrap_content-> MeasureSpec.AT_MOST
        //match_parent -> MeasureSpec.EXACTLY
        //具体值 -> MeasureSpec.EXACTLY
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int height = 0;
        int width = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++)
        {
            //测量子空间
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            //获取child对应的 layoutParams实例 并将其强转成MarginlayoutParams
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            //获得子控件的高度和宽度
            int childHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int childWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            //得到最大宽度，并且累加高度
            height += childHeight;
            width = Math.max(childWidth, width);

        }

        setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ?
                        measureWidth : width,
                (measureHeightMode == MeasureSpec.EXACTLY) ?
                        measureHeight : height);

    }

    /**
     * 实现所有子空间的布局
     * 根据自的意愿把内部的各个空间进行排列
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        //控件的顶点
        int top = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++)
        {
            View child = getChildAt(i);

            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();

            int childHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int childWidth = child.getMeasuredWidth() + layoutParams.rightMargin + layoutParams.leftMargin;

            child.layout(0, top, childWidth, top + childHeight);
            top += childHeight;
        }
    }



}
