package com.hitsz.aircraftwar.prop;

import com.hitsz.aircraftwar.aircraft.HeroAircraft;

/**
 * @author HenryZ
 */
public class FireProp extends AbstractProp {

    public FireProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activateItem(HeroAircraft heroAircraft) {
        heroAircraft.setFireActivated(true);
    }
}
