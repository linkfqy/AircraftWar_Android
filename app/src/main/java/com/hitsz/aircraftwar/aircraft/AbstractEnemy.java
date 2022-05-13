package com.hitsz.aircraftwar.aircraft;

import com.hitsz.aircraftwar.application.MainActivity;
import com.hitsz.aircraftwar.prop.AbstractProp;
import com.hitsz.aircraftwar.prop.creator.ItemBloodCreator;
import com.hitsz.aircraftwar.prop.creator.ItemBombCreator;
import com.hitsz.aircraftwar.prop.creator.ItemFireCreator;
import com.hitsz.aircraftwar.prop.creator.ItemCreator;

/**
 * @author henry
 */
public abstract class AbstractEnemy extends AbstractAircraft{


    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.hp = hp;
        this.maxHp = hp;
    }

    /** 产生道具 */
    public AbstractProp dropItems(double[] thresh){
        double i = Math.random();
        if(i<thresh[0]) {
            return null;
        } else if(i<thresh[1]){
            ItemCreator creator = new ItemBloodCreator();
            return creator.createItem(locationX, locationY, 0, 4);
        } else if(i<thresh[2]){
            ItemCreator creator = new ItemFireCreator();
            return creator.createItem(locationX, locationY, 0, 4);
        } else{
            ItemCreator creator = new ItemBombCreator();
            return creator.createItem(locationX, locationY, 0, 4);
        }
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= MainActivity.WINDOW_HEIGHT ) {
            vanish();
        }
    }

}
