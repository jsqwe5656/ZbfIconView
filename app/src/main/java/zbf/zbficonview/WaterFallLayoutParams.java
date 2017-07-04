package zbf.zbficonview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 *
 * Created by zbf on 2017/7/4.
 */
public class WaterFallLayoutParams extends ViewGroup.LayoutParams
{
    public int left = 0;
    public int top = 0;
    public int right = 0;
    public int bottom = 0;

    public WaterFallLayoutParams(Context c, AttributeSet attrs)
    {
        super(c, attrs);
    }

    public WaterFallLayoutParams(int width, int height)
    {
        super(width, height);
    }

    public WaterFallLayoutParams(ViewGroup.LayoutParams source)
    {
        super(source);
    }


}
