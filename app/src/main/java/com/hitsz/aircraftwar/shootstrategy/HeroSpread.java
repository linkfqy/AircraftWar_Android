package com.hitsz.aircraftwar.shootstrategy;

import com.hitsz.aircraftwar.bullet.BaseBullet;
import com.hitsz.aircraftwar.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;


/**
 * @author henry
 */
public class HeroSpread implements AbstractShootStrategy {

    @Override
    public List<BaseBullet> executeStrategy(int x, int y, int speedX, int speedY) {
        List<BaseBullet> res = new LinkedList<>();
        BaseBullet baseBullet;
        int shootNum = 3;
        int power = 30;
        int direction = -1;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            baseBullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y + direction*2, speedX + (i*2 - shootNum + 1), speedY + direction*20, power);
            res.add(baseBullet);
        }
        return res;
    }
}
