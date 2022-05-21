package com.hitsz.aircraftwar.application;

import android.app.Service;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.hitsz.aircraftwar.R;

import java.util.HashMap;

public class MusicService extends Service {

    private static final String TAG = "MusicService";
    private final HashMap<Integer, Integer> soundID = new HashMap<Integer, Integer>();
    private SoundPool mSoundPool;
    private MediaPlayer player;
    private MediaPlayer playerBgmBoss;

    public MusicService() {}

    @Override
    public void onCreate() {
        super.onCreate();
        mSoundPool = new SoundPool.Builder()
                        .setMaxStreams(6)
                        .build();
        soundID.put(1, mSoundPool.load(this, R.raw.bullet_hit, 1));
        soundID.put(2, mSoundPool.load(this, R.raw.game_over, 1));
        soundID.put(3, mSoundPool.load(this, R.raw.get_supply, 1));
        soundID.put(4, mSoundPool.load(this, R.raw.bomb_explosion, 1));
    }

    // 循环播放背景音乐
    private void playBgm(){
        if(player == null){
            player = MediaPlayer.create(this, R.raw.bgm);
            player.setLooping(true);
        }
        player.start();
    }

    // 停止播放背景音乐
    private void stopBgm() {
        if (player != null) {
            player.stop();
            player.reset();//重置
            player.release();//释放
            player = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopBgm();
    }

    @Override
    public IBinder onBind(Intent intent) {
        playBgm();
        return new MyBinder();
    }

    public class MyBinder extends Binder {

        public void playBulletHit(){
            mSoundPool.play(soundID.get(1), 1, 1, 0,0,1);
        }

        public void playGameOver(){
            mSoundPool.play(soundID.get(2), 1, 1, 0, 0, 1);
        }

        public void playGetSupply(){
            mSoundPool.play(soundID.get(3), 1, 1, 0,0,1);
        }

        public void playBombExplosion(){
            mSoundPool.play(soundID.get(4), 1, 1, 0, 0, 1);
        }

        // 循环播放boss背景音乐
        public void playBgmBoss(){
            if(playerBgmBoss == null){
                playerBgmBoss = MediaPlayer.create(MusicService.this, R.raw.bgm_boss);
                playerBgmBoss.setLooping(true);
            }
            playerBgmBoss.start();
        }

        // 停止播放boss背景音乐
        public void stopBgmBoss() {
            if (playerBgmBoss != null) {
                playerBgmBoss.stop();
                playerBgmBoss.reset();//重置
                playerBgmBoss.release();//释放
                playerBgmBoss = null;
            }
        }
    }
}
