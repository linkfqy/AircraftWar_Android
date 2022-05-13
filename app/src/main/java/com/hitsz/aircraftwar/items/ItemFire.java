package com.hitsz.aircraftwar.items;

import com.hitsz.aircraftwar.aircraft.HeroAircraft;

/**
 * @author HenryZ
 */
public class ItemFire extends AbstractItem {

    public ItemFire(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activateItem(HeroAircraft heroAircraft) {
        heroAircraft.setFireActivated(true);
    }
}
