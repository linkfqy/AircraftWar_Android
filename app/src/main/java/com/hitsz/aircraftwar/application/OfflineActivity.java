package com.hitsz.aircraftwar.application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.hitsz.aircraftwar.R;

public class OfflineActivity extends AppCompatActivity {

    private Button btnEasy;
    private Button btnNormal;
    private Button btnHard;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);

        // 进入难度选择界面
        btnEasy = findViewById(R.id.easy_btn);
        btnNormal = findViewById(R.id.normal_btn);
        btnHard = findViewById(R.id.hard_btn);

        // 进入游戏界面
        btnEasy.setOnClickListener(v -> {
            gameMode = OfflineActivity.GameMode.EASY;
            startGameActivity();

        });
        btnNormal.setOnClickListener(v -> {
            gameMode = OfflineActivity.GameMode.NORMAL;
            startGameActivity();
        });
        btnHard.setOnClickListener(v -> {
            gameMode = OfflineActivity.GameMode.HARD;
            startGameActivity();
        });
    }

    private void startGameActivity(){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("playMusic", (MainActivity.myBinder !=null));
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

}