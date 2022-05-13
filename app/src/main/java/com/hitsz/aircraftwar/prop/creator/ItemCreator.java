package com.hitsz.aircraftwar.prop.creator;

import com.hitsz.aircraftwar.prop.AbstractProp;

/**
 *
 * @author HenryZ
 */
public interface ItemCreator {
    /**
     * 创建道具的抽象方法
     * @param locationX
     * @param locationY
     * @param speedX
     * @param speedY
     * @return
     */
    AbstractProp createItem(int locationX, int locationY, int speedX, int speedY);
}
