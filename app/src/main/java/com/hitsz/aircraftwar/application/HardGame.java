package com.hitsz.aircraftwar.application;

import android.content.Context;

import com.hitsz.aircraftwar.aircraft.creator.BossCreator;
import com.hitsz.aircraftwar.aircraft.creator.EnemyCreator;

import java.util.Map;

/**
 * @author henry
 */
public class HardGame extends GameView{

    public HardGame(Context context){
        super(context);

        // 设置游戏参数
        backgroundImage=ImageManager.BACKGROUND_IMAGE_HARD;
        enemyMaxNumber = 9;
        bossAppearThreshold = 400;
        dropItemThresh = new double[]{0.4, 0.7, 0.85};
        eliteAppearThreshold = 0.5;
        enemyCycleDuration = 400;
        heroCycleDuration = 250;
        mobParam.putAll(Map.of(
                "speedX", 0,
                "speedY", 8,
                "hp",     40
        ));
        eliteParam.putAll(Map.of(
                "speedX", 2,
                "speedY", 8,
                "hp",     40
        ));
        bossParam.putAll(Map.of(
                "speedX", 2,
                "speedY", 0,
                "hp",     400
        ));
    }

    @Override
    protected void generateEnemy() {
        double i = Math.random();
        if(bossAppearFlag >= bossAppearThreshold){
            generateBoss();
        }
        else if(i<eliteAppearThreshold){
            generateMob();
        }
        else{
            generateElite();
        }
    }

    @Override
    protected void generateBoss() {
        bossAppearFlag -= bossAppearThreshold;
        EnemyCreator enemyCreator = new BossCreator();
        enemyAircrafts.add(enemyCreator.createEnemy(
                (int) (Math.random() * (MainActivity.WINDOW_WIDTH - ImageManager.BOSS_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * MainActivity.WINDOW_HEIGHT * 0.2),
                bossParam.get("speedX"),
                bossParam.get("speedY"),
                bossParam.get("hp")
        ));
        bossParam.replace("hp", bossParam.get("hp")+100);
        System.out.println("下次boss敌机血量加100");
        generateBossMusic(playMusic);
    }

    @Override
    protected void difficultyUpdateCheck() {
        if (time > 0 && time % difficultyUpdateCycle == 0) {
            if(mobParam.get("hp") < 90){
                mobParam.replace("hp", (int)(eliteParam.get("hp")*1.5));
                eliteParam.replace("hp", (int)(eliteParam.get("hp")*1.5));
                bossParam.replace("hp", (int)(eliteParam.get("hp")*1.5));
                System.out.println("难度增强，敌机血量增加1.5倍");
            }
            // 否则靠增加敌机数量上限增加难度,最大不超过12
            else if(enemyMaxNumber <= 12){
                enemyMaxNumber += 1;
                System.out.println("难度增强，敌机数量上限+1");
            }
            // 否则降低boss出现阈值
            else{
                bossAppearThreshold = Math.max(bossAppearFlag-50, 200);
            }
        }
    }
}
