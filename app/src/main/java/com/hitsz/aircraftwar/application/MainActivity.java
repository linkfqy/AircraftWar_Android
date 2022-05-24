package com.hitsz.aircraftwar.application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.hitsz.aircraftwar.R;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnEasy;
    private Button btnNormal;
    private Button btnHard;
    private SwitchCompat swMusic;

    public static MusicService.MyBinder myBinder;
    private Connect conn;

    // 游戏设定：难度，音效
    public enum GameMode{
        /**
         * 简单、普通、困难三种模式
         */
        EASY,NORMAL,HARD;
        public String getFileName(){
            switch (this){
                case EASY:return "EasyGameRecords.dat";
                case NORMAL:return "NormalGameRecords.dat";
                default:return "HardGameRecords.dat";
            }
        }
    }
    public volatile static GameMode gameMode;

    public static int WINDOW_HEIGHT;
    public static int WINDOW_WIDTH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityManager.getIns().add(this);

        // 初始化屏幕参数
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        WINDOW_HEIGHT = dm.heightPixels;
        WINDOW_WIDTH =dm.widthPixels;

        // 初始化图片资源
        ImageManager.initial(getResources());

        // 进入开始界面
        btnEasy = findViewById(R.id.easy_btn);
        btnNormal = findViewById(R.id.normal_btn);
        btnHard = findViewById(R.id.hard_btn);
        swMusic = findViewById(R.id.music_switch);

        // 进入游戏界面
        btnEasy.setOnClickListener(v -> {
            gameMode = GameMode.EASY;
            startGameActivity();
        });
        btnNormal.setOnClickListener(v -> {
            gameMode = GameMode.NORMAL;
            startGameActivity();
        });
        btnHard.setOnClickListener(v -> {
            gameMode = GameMode.HARD;
            startGameActivity();
        });
    }

    private void startGameActivity(){
        if(swMusic.isChecked()){
            conn = new Connect();
            Intent intent = new Intent(this, MusicService.class);
            bindService(intent, conn, Context.BIND_AUTO_CREATE);
        }

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("playMusic", swMusic.isChecked());
        // 想要得到Game的分数
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        int score=data.getIntExtra("score",0);
        // 向RankingActivity传入分数
        startActivity(new Intent(this,RankingActivity.class).putExtra("score",score));
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unbindService(conn);
    }

    static class Connect implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service){
            myBinder = (MusicService.MyBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}