package com.hitsz.aircraftwar.aircraft.creator;


import com.hitsz.aircraftwar.aircraft.AbstractEnemy;
import com.hitsz.aircraftwar.aircraft.EliteEnemy;

public class EliteCreator implements EnemyCreator {
    private static int direction=1;
    @Override
    public AbstractEnemy createEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        direction = direction == 1 ? -1 : 1;
        return new EliteEnemy(locationX, locationY, speedX*direction, speedY, hp);
    }
}
