package com.hitsz.aircraftwar.prop;

import com.hitsz.aircraftwar.aircraft.HeroAircraft;
import com.hitsz.aircraftwar.application.MainActivity;
import com.hitsz.aircraftwar.basic.AbstractFlyingObject;

public abstract class AbstractProp extends AbstractFlyingObject {

    public AbstractProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= MainActivity.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    /**
     * 激活道具效果
     */
    public abstract void activateItem(HeroAircraft heroAircraft);
}
