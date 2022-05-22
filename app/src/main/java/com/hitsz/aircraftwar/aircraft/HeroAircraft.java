package com.hitsz.aircraftwar.aircraft;

import com.hitsz.aircraftwar.application.ImageManager;
import com.hitsz.aircraftwar.application.MainActivity;
import com.hitsz.aircraftwar.bullet.BaseBullet;
import com.hitsz.aircraftwar.shootstrategy.AbstractShootStrategy;
import com.hitsz.aircraftwar.shootstrategy.HeroSpread;
import com.hitsz.aircraftwar.shootstrategy.HeroStraight;

import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    /**
     * 是否火力加成
     */
    private boolean fireActivated = false;

    /** 英雄机的唯一实例 */
    private static final HeroAircraft heroAircraft = new HeroAircraft(
            MainActivity.WINDOW_WIDTH / 2,
            MainActivity.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight(),
            0, 0, 1000);

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    /**
     * 参数在HeroAircraft中修改
     * @return 英雄机的唯一实例
     */
    public static HeroAircraft getHeroAircraft(){
        return heroAircraft;
    }

    /** 加血 */
    public void increaseHp(int increment){
        this.hp = Math.min(this.hp+increment, maxHp);
    }

    /** 修改火力增强标志 */
    public void setFireActivated(Boolean flag){
        fireActivated = flag;
    }

    /** 查看火力增强标志 */
    public Boolean getFireActivated(){
        return fireActivated;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    public void initial() {
        this.hp=maxHp;
        this.setLocation(MainActivity.WINDOW_WIDTH / 2.0,
                MainActivity.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight());
    }

    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    @Override
    public List<BaseBullet> shoot() {
        AbstractShootStrategy abstractStrategy;
        if(fireActivated){
            abstractStrategy = new HeroSpread();
        }
        else{
            abstractStrategy = new HeroStraight();
        }
        return abstractStrategy.executeStrategy(
                getLocationX(),
                getLocationY(),
                0,
                getSpeedY()
        );
    }
}
