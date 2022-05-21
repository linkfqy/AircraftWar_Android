package com.hitsz.aircraftwar.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.hitsz.aircraftwar.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnEasy;
    private Button btnNormal;
    private Button btnHard;
    private SwitchCompat swMusic;


    // 游戏设定：难度，音效
    public static String difficulty;
    public static Boolean playMusic;
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
            startGameActivity();
        });
        btnNormal.setOnClickListener(v -> {
            difficulty = "NORMAL";
            startGameActivity();
        });
        btnHard.setOnClickListener(v -> {
            difficulty = "HARD";
            startGameActivity();
        });
    }

    private void startGameActivity(){
        playMusic = swMusic.isChecked();
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}