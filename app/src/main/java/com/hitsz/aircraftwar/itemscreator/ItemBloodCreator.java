package com.hitsz.aircraftwar.itemscreator;

import com.hitsz.aircraftwar.items.AbstractItem;
import com.hitsz.aircraftwar.items.ItemBlood;

public class ItemBloodCreator implements ItemCreator {
    @Override
    public AbstractItem createItem(int locationX, int locationY, int speedX, int speedY) {
        return new ItemBlood(locationX, locationY, speedX, speedY);
    }
}
