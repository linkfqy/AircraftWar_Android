package com.hitsz.aircraftwar.prop;

import com.hitsz.aircraftwar.aircraft.HeroAircraft;
import com.hitsz.aircraftwar.basic.AbstractFlyingObject;
import com.hitsz.aircraftwar.bullet.BaseBullet;

import java.util.ArrayList;
import java.util.List;

public class BombProp extends AbstractProp {

    private ArrayList<AbstractFlyingObject> subscriber = new ArrayList<>();

    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public void addSubscriber(AbstractFlyingObject obj){
        subscriber.add(obj);
    }

    public void addAllSubscriber(List<BaseBullet> obj){
        subscriber.addAll(obj);
    }

    public void deleteSubscriber(AbstractFlyingObject obj){
        subscriber.remove(obj);
    }

    private void notifySubscriber(){
        for(AbstractFlyingObject obj : subscriber){
            obj.vanish();
        }
    }

    public ArrayList<AbstractFlyingObject> getSubscriber() {
        return subscriber;
    }

    @Override
    public void activateItem(HeroAircraft heroAircraft) {
        notifySubscriber();
    }
}
