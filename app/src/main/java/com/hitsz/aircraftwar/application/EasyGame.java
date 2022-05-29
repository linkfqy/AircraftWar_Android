package com.hitsz.aircraftwar.application;

import android.content.Context;

import java.util.Map;

/**
 * @author henry
 */
public class EasyGame extends GameView{

    public EasyGame(Context context){
        super(context);

        // 设置游戏参数
        backgroundImage=ImageManager.BACKGROUND_IMAGE_EASY;
        enemyMaxNumber = 5;
        dropPropThresh = new double[]{0.5, 0.7, 0.85};
        eliteAppearThreshold = 0.4;
        enemyCycleDuration = 400;
        heroCycleDuration = 250;
        mobParam.putAll(Map.of(
                "speedX", 0,
                "speedY", 10,
                "hp",     30
        ));
        eliteParam.putAll(Map.of(
                "speedX", 2,
                "speedY", 10,
                "hp",     30
        ));
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
}
