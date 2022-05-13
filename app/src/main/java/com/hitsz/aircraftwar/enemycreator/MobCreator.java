package com.hitsz.aircraftwar.enemycreator;

import com.hitsz.aircraftwar.aircraft.AbstractEnemy;
import com.hitsz.aircraftwar.aircraft.MobEnemy;

public class MobCreator implements EnemyCreator{
    @Override
    public AbstractEnemy createEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        return new MobEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
