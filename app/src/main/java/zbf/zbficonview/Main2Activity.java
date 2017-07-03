package zbf.zbficonview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import zbf.zbficonview.viewgroup.WaterFallLayout;

public class Main2Activity extends AppCompatActivity
{
    Button btn_test1;
    WaterFallLayout waterFallLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        waterFallLayout = (WaterFallLayout) findViewById(R.id.waterfall);

        btn_test1 = (Button) findViewById(R.id.btn_test1);
        btn_test1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addView(waterFallLayout);
            }
        });
    }

    private void addView(WaterFallLayout layout)
    {
        Random random = new Random();
        Integer num = Math.abs(random.nextInt());
        WaterFallLayout.LayoutParams layoutParams = new WaterFallLayout.LayoutParams(WaterFallLayout.LayoutParams.WRAP_CONTENT,
                WaterFallLayout.LayoutParams.WRAP_CONTENT);
        ImageView imageView = new ImageView(this);
        int which = num % 5;
        switch (which)
        {
            case 0:
                imageView.setImageResource(R.mipmap.pic_1);
                break;
            case 1:
                imageView.setImageResource(R.mipmap.pic_2);
                break;
            case 2:
                imageView.setImageResource(R.mipmap.pic_3);
                break;
            case 3:
                imageView.setImageResource(R.mipmap.pic_4);
                break;
            case 4:
                imageView.setImageResource(R.mipmap.pic_5);
                break;
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        layout.addView(imageView,layoutParams);

        waterFallLayout.setOnItemClickListener(new WaterFallLayout.OnItemClickListener()
        {
            @Override
            public void onItemClick(View v, int index)
            {
                Toast.makeText(Main2Activity.this,"item=" + index,Toast.LENGTH_SHORT).show();
            }
        });

    }

}
