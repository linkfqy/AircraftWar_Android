package com.hitsz.aircraftwar.aircraft;

import com.hitsz.aircraftwar.bullet.BaseBullet;
import com.hitsz.aircraftwar.shootstrategy.AbstractShootStrategy;
import com.hitsz.aircraftwar.shootstrategy.EliteStraight;

import java.util.List;

public class EliteEnemy extends AbstractEnemy{

    /**精英敌机的构造函数*/
    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public List<BaseBullet> shoot() {
        AbstractShootStrategy abstractStrategy = new EliteStraight();
        return abstractStrategy.executeStrategy(
                getLocationX(),
                getLocationY(),
                0,
                getSpeedY()
        );
    }

}
