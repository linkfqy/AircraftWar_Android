package com.hitsz.aircraftwar.application;

import android.content.Context;

import com.hitsz.aircraftwar.aircraft.creator.BossCreator;
import com.hitsz.aircraftwar.aircraft.creator.EnemyCreator;

import java.util.Map;


/**
 * @author henry
 */
public class NormalGame extends GameView{

    public NormalGame(Context context){
        super(context);

        // 设置游戏参数
        backgroundImage = ImageManager.BACKGROUND_IMAGE_NORMAL;
        enemyMaxNumber = 7;
        bossAppearThreshold = 500;
        dropPropThresh = new double[]{0.5, 0.7, 0.85};
        eliteAppearThreshold = 0.45;
        enemyCycleDuration = 300;
        heroCycleDuration = 200;
        mobParam.putAll(Map.of(
                "speedX", 0,
                "speedY", 12,
                "hp",     30
        ));
        eliteParam.putAll(Map.of(
                "speedX", 2,
                "speedY", 12,
                "hp",     30
        ));
        bossParam.putAll(Map.of(
                "speedX", 2,
                "speedY", 0,
                "hp",     800
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
        generateBossMusic();
    }

    @Override
    protected void difficultyUpdateCheck() {
        if(time > 0 && time % difficultyUpdateCycle == 0){
            // 普通敌机血量小于120时靠增加血量增强难度
            if (mobParam.get("hp") < 90) {
                mobParam.replace("hp", (int) (eliteParam.get("hp") * 1.3));
                eliteParam.replace("hp", (int) (eliteParam.get("hp") * 1.3));
                bossParam.replace("hp", (int) (eliteParam.get("hp") * 1.3));
                System.out.println("难度增强，敌机血量增加1.3倍");
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
