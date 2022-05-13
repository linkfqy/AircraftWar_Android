package com.hitsz.aircraftwar.application;

import androidx.appcompat.app.AppCompatActivity;
import com.hitsz.aircraftwar.R;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.hitsz.aircraftwar.R;

public class MainActivity extends AppCompatActivity {

    public static int WINDOW_HEIGHT;
    public static int WINDOW_WIDTH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化屏幕参数
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        WINDOW_HEIGHT = dm.heightPixels;
        WINDOW_WIDTH =dm.widthPixels;

        ImageManager.initial(getResources());
        GameView game=new GameView(this);
        setContentView(game);
    }
}