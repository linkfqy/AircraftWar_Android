package com.hitsz.aircraftwar.application;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.hitsz.aircraftwar.R;

import java.util.HashMap;
import java.util.Map;

public class ImageManager {
    private static final Map<String, Bitmap> CLASS_IMAGE_MAP = new HashMap<>();
    private static Bitmap HERO = null;
    private static Bitmap MOB = null;
    private static Bitmap ELITE = null;
    private static Bitmap BOSS = null;

    public static void initial(Resources resources){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        try {

            HERO = BitmapFactory.decodeResource(resources, R.drawable.hero);
            MOB = BitmapFactory.decodeResource(resources, R.drawable.mob);
            ELITE = BitmapFactory.decodeResource(resources, R.drawable.elite);
            BOSS = BitmapFactory.decodeResource(resources, R.drawable.boss, options);

            CLASS_IMAGE_MAP.put("HeroAircraft", HERO);
            CLASS_IMAGE_MAP.put("Mob", MOB);
            CLASS_IMAGE_MAP.put("Elite", ELITE);
            CLASS_IMAGE_MAP.put("Boss", BOSS);
        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static Bitmap get(String className){
        return CLASS_IMAGE_MAP.get(className);
    }

    public static Bitmap get(Object obj){
        if(obj == null){
            return null;
        }
        else{
            return get(obj.getClass().getName());
        }
    }
}
