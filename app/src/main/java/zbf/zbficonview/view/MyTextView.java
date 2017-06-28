package zbf.zbficonview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import zbf.zbficonview.R;

/**
 * 尝试自定义添加控件属性
 * Created by zbf on 2017/6/28.
 */

public class MyTextView extends TextView
{
    public MyTextView(Context context)
    {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        float headerHeight = typedArray.getDimension(R.styleable.MyTextView_headerHeight,-1);
        int age = typedArray.getInt(R.styleable.MyTextView_age,-1);
        //使用完成后必须释放资源
        typedArray.recycle();
        this.setText("headerHeight:" + headerHeight + ",age:" + age);
    }

    /**
     * 根据container内部的子控件计算自己的宽和高
     *       父类传过来给当前view的建议值 把当前view的尺寸设置成传递过来的值
     *       两个参数转换成二进制 前两位代表模式 后28位代表数字
     *       共有三种模式
     *       ①、UNSPECIFIED(未指定)，父元素部队自元素施加任何束缚，子元素可以得到任意想要的大小；
     *       ②、EXACTLY(完全)，父元素决定自元素的确切大小，子元素将被限定在给定的边界里而忽略它本身大小；
     *       ③、AT_MOST(至多)，子元素至多达到指定大小的值。
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //分别获取两个值的mode和数值
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }

    /**
     * 实现所有子空间布局
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
    }
}
