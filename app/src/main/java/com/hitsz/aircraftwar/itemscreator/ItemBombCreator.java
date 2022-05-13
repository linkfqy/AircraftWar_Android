package com.hitsz.aircraftwar.itemscreator;

import com.hitsz.aircraftwar.items.AbstractItem;
import com.hitsz.aircraftwar.items.ItemBomb;

public class ItemBombCreator implements ItemCreator {
    @Override
    public AbstractItem createItem(int locationX, int locationY, int speedX, int speedY) {
        return new ItemBomb(locationX, locationY, speedX, speedY);
    }
}
