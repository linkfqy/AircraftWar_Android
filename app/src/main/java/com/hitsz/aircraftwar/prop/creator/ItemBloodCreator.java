package com.hitsz.aircraftwar.prop.creator;

import com.hitsz.aircraftwar.prop.AbstractProp;
import com.hitsz.aircraftwar.prop.LifeProp;

public class ItemBloodCreator implements ItemCreator {
    @Override
    public AbstractProp createItem(int locationX, int locationY, int speedX, int speedY) {
        return new LifeProp(locationX, locationY, speedX, speedY);
    }
}
