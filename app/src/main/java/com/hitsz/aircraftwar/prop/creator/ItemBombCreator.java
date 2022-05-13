package com.hitsz.aircraftwar.prop.creator;

import com.hitsz.aircraftwar.prop.AbstractProp;
import com.hitsz.aircraftwar.prop.BombProp;

public class ItemBombCreator implements ItemCreator {
    @Override
    public AbstractProp createItem(int locationX, int locationY, int speedX, int speedY) {
        return new BombProp(locationX, locationY, speedX, speedY);
    }
}
