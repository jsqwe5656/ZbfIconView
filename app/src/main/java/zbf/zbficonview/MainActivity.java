package zbf.zbficonview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import zbf.zbficonview.view.MyCircleView;
import zbf.zbficonview.view.MyQuadView;
import zbf.zbficonview.view.MyRegionView;
import zbf.zbficonview.view.MyTouchView;
import zbf.zbficonview.view.MyWaveView;

public class MainActivity extends AppCompatActivity
{
    Button reset;
    MyTouchView touchView;
    MyWaveView waveView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout root = (LinearLayout) findViewById(R.id.frame);
//        root.addView(new MyCircleView(this));
        root.addView(new MyRegionView(this));
        getLoaderManager().initLoader(0, null, new DataLoaderCallback());
        root.addView(new MyQuadView(this));

        reset = (Button) findViewById(R.id.btn_reset);
        touchView = (MyTouchView) findViewById(R.id.touchview);
        reset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                touchView.reset();

            }
        });

        waveView = (MyWaveView) findViewById(R.id.waveview);
        waveView.startAnim();

    }
}
