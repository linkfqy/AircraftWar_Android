package com.hitsz.aircraftwar.shootstrategy;

import com.hitsz.aircraftwar.bullet.BaseBullet;

import java.util.List;

/**
 * @author henry
 */
public interface AbstractShootStrategy {

    /**
     * 执行策略
     * @param x 飞机x坐标
     * @param  y 飞机y坐标
     * @param speedX 飞机x速度
     * @param speedY 飞机y速度
     * @return 返回子弹列表
     */
    List<BaseBullet> executeStrategy(int x, int y, int speedX, int speedY);
}
