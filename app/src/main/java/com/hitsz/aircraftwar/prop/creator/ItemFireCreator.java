package com.hitsz.aircraftwar.prop.creator;

import com.hitsz.aircraftwar.prop.AbstractProp;
import com.hitsz.aircraftwar.prop.FireProp;

public class ItemFireCreator implements ItemCreator {

    @Override
    public AbstractProp createItem(int locationX, int locationY, int speedX, int speedY) {
        return new FireProp(locationX, locationY, speedX, speedY);
    }
}
