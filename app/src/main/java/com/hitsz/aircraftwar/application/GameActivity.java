package com.hitsz.aircraftwar.application;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hitsz.aircraftwar.R;

public class GameActivity extends Activity {

    private GameView game;
    boolean musicOn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // 创建游戏实例
        switch(OfflineActivity.gameMode){
            case EASY:
                game = new EasyGame(this);
                break;
            case NORMAL:
                game = new NormalGame(this);
                break;
            case HARD:
                game = new HardGame(this);
                break;
            default:
                break;
        }

        musicOn=getIntent().getBooleanExtra("playMusic", false);
        game.setPlayMusic(musicOn);
        setContentView(game);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (musicOn){
            MainActivity.myBinder.stopBgm();
        }
    }
}
