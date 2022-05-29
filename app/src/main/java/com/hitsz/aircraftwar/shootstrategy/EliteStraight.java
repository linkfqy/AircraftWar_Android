package com.hitsz.aircraftwar.shootstrategy;

import com.hitsz.aircraftwar.bullet.BaseBullet;
import com.hitsz.aircraftwar.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * @author henry
 */
public class EliteStraight implements AbstractShootStrategy {

    @Override
    public List<BaseBullet> executeStrategy(int x, int y, int speedX, int speedY) {
        List<BaseBullet> res = new LinkedList<>();
        BaseBullet baseBullet;
        int shootNum = 1;
        int power =30;
        int direction = 1;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            baseBullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y + direction*2, speedX, speedY + direction*10, power);
            res.add(baseBullet);
        }
        return res;
    }
}
