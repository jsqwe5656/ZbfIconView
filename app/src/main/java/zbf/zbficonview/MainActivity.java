package zbf.zbficonview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import zbf.zbficonview.view.MyCircleView;
import zbf.zbficonview.view.MyRegionView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout root = (FrameLayout) findViewById(R.id.frame);
//        root.addView(new MyCircleView(this));
        root.addView(new MyRegionView(this));


    }
}
