package zbf.zbficonview;

import android.content.Loader;
import android.os.Bundle;

/**
 * 需要自我实现的Loader回调
 * Created by zbf on 2017/6/19.
 */

class DataLoaderCallback implements android.app.LoaderManager.LoaderCallbacks<Object>
{
    //实例化并返回一个新创建给指定ID的Loader对象；第一启动时调用
    @Override
    public Loader<Object> onCreateLoader(int id, Bundle args)
    {
        return null;
    }

    //load完成之后回调此方法；每次都调用
    @Override
    public void onLoadFinished(Loader<Object> loader, Object data)
    {

    }

    //当创建好的Loader被reset时调用此方法，会清空已绑定数据，此时CreatLoader会重新执行
    @Override
    public void onLoaderReset(Loader<Object> loader)
    {

    }
}
