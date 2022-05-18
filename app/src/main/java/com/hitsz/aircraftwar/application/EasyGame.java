package com.hitsz.aircraftwar.application;

import android.content.Context;

import java.util.Map;

/**
 * @author henry
 */
public class EasyGame extends GameView{

    // 设置游戏参数
    static{
        enemyMaxNumber = 5;
        dropItemThresh = new double[]{0.3, 0.6, 0.8};
        eliteAppearThreshold = 0.4;
        enemyCycleDuration = 400;
        heroCycleDuration = 250;
        mobParam.putAll(Map.of(
                "speedX", 0,
                "speedY", 8,
                "hp",     30
        ));
        eliteParam.putAll(Map.of(
                "speedX", 2,
                "speedY", 8,
                "hp",     30
        ));
    }

    public EasyGame(Context context){
        super(context);
    }

    @Override
    protected void generateEnemy() {
        double i = Math.random();
        if(i<eliteAppearThreshold){
            generateMob();
        }
        else{
            generateElite();
        }
    }

    @Override
    protected void generateBoss() {}

    @Override
    protected void difficultyUpdateCheck() {}

    @Override
    protected void paintBackground() {
        canvas.drawBitmap(ImageManager.BACKGROUND_IMAGE_EASY,
                            0,
                            backgroundTop-ImageManager.BACKGROUND_IMAGE_EASY.getHeight(),
                            paint);
        canvas.drawBitmap(ImageManager.BACKGROUND_IMAGE_EASY,
                            0,
                            backgroundTop,
                            paint);
    }
}
