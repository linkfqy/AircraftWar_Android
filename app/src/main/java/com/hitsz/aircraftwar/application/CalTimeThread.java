package com.hitsz.aircraftwar.application;

import com.hitsz.aircraftwar.aircraft.HeroAircraft;

/**
 * @author henry
 */
public class CalTimeThread extends Thread{

    HeroAircraft heroAircraft = HeroAircraft.getHeroAircraft();

    @Override
    public void run() {
        int activateTime = 15000;
        try {
            sleep(activateTime);
            heroAircraft.setFireActivated(false);
        } catch (InterruptedException ignored) {

        }
    }
}
