package com.github.maxwell.nc.starratingview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.maxwell.nc.library.StarRatingView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sampleCtrl();
    }

    /**
     * 示例控制
     */
    private void sampleCtrl() {
        StarRatingView starRatingView = (StarRatingView) findViewById(R.id.star_rating);

        //评分
        starRatingView.rate(7);

        //获取评分
        starRatingView.getRatingCount();

        //获取总分
        starRatingView.getStarCount();

        //设置监听器
        starRatingView.setOnRateChangeListener(new StarRatingView.OnRateChangeListener() {
            @Override
            public void onRated(int rateCount) {
                Toast.makeText(MainActivity.this, "rate:" + rateCount, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
