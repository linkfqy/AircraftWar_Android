package com.hitsz.aircraftwar.application;

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
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private SwitchCompat swMusic;
    private Button btnOffline;
    private Button btnOnline;

    public static MusicService.MyBinder myBinder;
    private MainActivity.Connect conn;

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

        // 初始化按钮
        swMusic = findViewById(R.id.music_switch);
        btnOffline = findViewById(R.id.offline);
        btnOnline = findViewById(R.id.online);

        // 按钮监听
        btnOffline.setOnClickListener( view -> {
            checkMusic();
            Intent intent = new Intent(this, OfflineActivity.class);
            startActivity(intent);
        });

        btnOnline.setOnClickListener( view -> {
            checkMusic();
            Intent intent = new Intent(this, OnlineActivity.class);
            startActivity(intent);
        });
    }

    private void checkMusic(){
        if(swMusic.isChecked()){
            conn = new Connect();
            Intent intent = new Intent(this, MusicService.class);
            bindService(intent, conn, Context.BIND_AUTO_CREATE);
        }
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