package com.hitsz.aircraftwar.application;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理各Activity的类
 * @author linkfqy
 */
public class ActivityManager {
    private List<Activity> aList;

    /**
     * 单例模式隐藏构造方法
     * @author linkfqy
     */
    private ActivityManager(){
        aList = new ArrayList<>();
    }

    private static final class InstanceHolder {
        static final ActivityManager instance = new ActivityManager();
    }

    public static ActivityManager getIns(){
        return InstanceHolder.instance;
    }

    public Activity get(int index){
        return aList.get(index);
    }

    public void add(Activity activity){
        aList.add(activity);
    }

    public void del(int index){
        aList.get(index).finish();
        aList.remove(index);
    }
}
