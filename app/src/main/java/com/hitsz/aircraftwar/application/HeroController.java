package com.hitsz.aircraftwar.application;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import com.hitsz.aircraftwar.aircraft.HeroAircraft;

/**
 * 英雄飞机控制类
 * 监听触摸屏，控制英雄机的移动
 * @author linkfqy
 */
public class HeroController implements View.OnTouchListener {
    private GameView game;
    private HeroAircraft heroAircraft;

    public HeroController(GameView game,HeroAircraft heroAircraft){
        this.game=game;
        this.heroAircraft=heroAircraft;
    }

    /**
     * 触碰屏幕时的操作
     * @return true：view继续响应Touch操作；
     *         false：view不再响应Touch操作，此后再也接收不到MotionEvent
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            heroAircraft.setLocation(event.getX(), event.getY());
        }
        return true;
    }
}
