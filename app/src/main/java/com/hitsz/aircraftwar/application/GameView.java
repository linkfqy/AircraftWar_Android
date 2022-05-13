package com.hitsz.aircraftwar.application;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback,Runnable{
    private int screenWidth,screenHeight;
    private boolean mbLoop;
    private final SurfaceHolder surfaceHolder;
    private Canvas canvas;  //绘图的画布
    private final Paint paint;
    private int backgroundTop;
    public GameView(Context context){
        super(context);
        mbLoop=true;
        paint=new Paint();
        surfaceHolder =this.getHolder();
        surfaceHolder.addCallback(this);
        this.setFocusable(true);
        backgroundTop=0;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        screenWidth = width;
        screenHeight = height;
        Log.i("out", "surfaceChanged: "+screenWidth+screenHeight);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        mbLoop = false;
    }

    @Override
    public void run() {
        while (mbLoop){
            synchronized (surfaceHolder){
                draw();
            }
            try {
                Thread.sleep(40);
            }catch (Exception ignored){}
        }
    }

    private void draw() {
        canvas= surfaceHolder.lockCanvas();
        if (canvas==null) return;

        // 循环绘制背景图片
        canvas.drawBitmap(ImageManager.BACKGROUND_IMAGE_EASY,0,backgroundTop-MainActivity.WINDOW_HEIGHT, paint);
        canvas.drawBitmap(ImageManager.BACKGROUND_IMAGE_EASY,0,backgroundTop, paint);
        backgroundTop+=1;
        if (backgroundTop==MainActivity.WINDOW_HEIGHT) backgroundTop=0;

        // 提交canvas内容
        surfaceHolder.unlockCanvasAndPost(canvas);
    }
}
