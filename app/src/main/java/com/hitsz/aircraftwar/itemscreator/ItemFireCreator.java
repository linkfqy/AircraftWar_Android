package com.hitsz.aircraftwar.itemscreator;

import com.hitsz.aircraftwar.items.AbstractItem;
import com.hitsz.aircraftwar.items.ItemFire;

public class ItemFireCreator implements ItemCreator {

    @Override
    public AbstractItem createItem(int locationX, int locationY, int speedX, int speedY) {
        return new ItemFire(locationX, locationY, speedX, speedY);
    }
}
