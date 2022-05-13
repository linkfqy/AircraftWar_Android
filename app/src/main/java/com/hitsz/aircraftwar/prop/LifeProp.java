package com.hitsz.aircraftwar.prop;

import com.hitsz.aircraftwar.aircraft.HeroAircraft;

public class LifeProp extends AbstractProp {

    private int addHp = 20;

    public LifeProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activateItem(HeroAircraft heroAircraft) {
        heroAircraft.increaseHp(addHp);
    }
}
