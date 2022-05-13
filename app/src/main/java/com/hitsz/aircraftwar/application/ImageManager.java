package com.hitsz.aircraftwar.application;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.hitsz.aircraftwar.R;
import com.hitsz.aircraftwar.aircraft.BossEnemy;
import com.hitsz.aircraftwar.aircraft.EliteEnemy;
import com.hitsz.aircraftwar.aircraft.HeroAircraft;
import com.hitsz.aircraftwar.aircraft.MobEnemy;
import com.hitsz.aircraftwar.bullet.EnemyBullet;
import com.hitsz.aircraftwar.bullet.HeroBullet;
import com.hitsz.aircraftwar.prop.LifeProp;
import com.hitsz.aircraftwar.prop.BombProp;
import com.hitsz.aircraftwar.prop.FireProp;

import java.util.HashMap;
import java.util.Map;

public class ImageManager {

    private static final Map<String, Bitmap> CLASSNAME_IMAGE_MAP = new HashMap<>();

    public static Bitmap BACKGROUND_IMAGE_EASY;
    public static Bitmap BACKGROUND_IMAGE_NORMAL;
    public static Bitmap BACKGROUND_IMAGE_HARD;

    public static Bitmap HERO_IMAGE;
    public static Bitmap HERO_BULLET_IMAGE;

    public static Bitmap ENEMY_BULLET_IMAGE;
    public static Bitmap MOB_ENEMY_IMAGE;
    public static Bitmap ELITE_ENEMY_IMAGE;
    public static Bitmap BOSS_ENEMY_IMAGE;

    public static Bitmap PROP_LIFE_IMAGE;
    public static Bitmap PROP_BOMB_IMAGE;
    public static Bitmap PROP_FIRE_IMAGE;

    public static void initial(Resources resources){
        // TODO options设置
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {

            BACKGROUND_IMAGE_EASY = BitmapFactory.decodeResource(resources, R.drawable.bg, options);
            BACKGROUND_IMAGE_NORMAL = BitmapFactory.decodeResource(resources, R.drawable.bg2, options);
            BACKGROUND_IMAGE_HARD = BitmapFactory.decodeResource(resources, R.drawable.bg5, options);

            HERO_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.hero, options);
            MOB_ENEMY_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.mob, options);
            ELITE_ENEMY_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.elite, options);
            BOSS_ENEMY_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.boss, options);

            HERO_BULLET_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.bullet_hero, options);
            ENEMY_BULLET_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.bullet_enemy, options);

            PROP_LIFE_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.prop_life, options);
            PROP_FIRE_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.prop_fire, options);
            PROP_BOMB_IMAGE = BitmapFactory.decodeResource(resources, R.drawable.prop_bomb, options);

            CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE);
            CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), MOB_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(), ELITE_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BossEnemy.class.getName(), BOSS_ENEMY_IMAGE);

            CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), HERO_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), ENEMY_BULLET_IMAGE);

            CLASSNAME_IMAGE_MAP.put(LifeProp.class.getName(), PROP_LIFE_IMAGE);
            CLASSNAME_IMAGE_MAP.put(FireProp.class.getName(), PROP_FIRE_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BombProp.class.getName(), PROP_BOMB_IMAGE);

        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static Bitmap get(String className){
        return CLASSNAME_IMAGE_MAP.get(className);
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
