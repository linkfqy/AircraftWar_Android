package com.hitsz.aircraftwar.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.hitsz.aircraftwar.R;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnEasy;
    private Button btnNormal;
    private Button btnHard;
    private SwitchCompat swMusic;

    private GameView game;
    private String difficulty;

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

        // 初始化图片资源
        ImageManager.initial(getResources());

        // 进入开始界面
        btnEasy = findViewById(R.id.easy);
        btnNormal = findViewById(R.id.normal);
        btnHard = findViewById(R.id.hard);
        swMusic = findViewById(R.id.music);

        // 进入游戏界面
        btnEasy.setOnClickListener(v -> {
            difficulty = "EASY";
            game = new EasyGame(this);
            setContentView(game);
        });
        btnNormal.setOnClickListener(v -> {
            difficulty = "NORMAL";
            game = new NormalGame(this);
            setContentView(game);
        });
        btnHard.setOnClickListener(v -> {
            difficulty = "HARD";
            game = new HardGame(this);
            setContentView(game);
        });
    }
}