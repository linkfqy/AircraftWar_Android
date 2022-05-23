package com.hitsz.aircraftwar.dao;

import java.io.Serializable;
import java.util.Date;

/**
 * 游戏记录类，代表一条游戏记录
 * @author linkfqy
 */
public class Record implements Serializable {
    private final int id;
    private String name;
    private int score;
    private Date date;
    public Record(int id,String name,int score,Date date){
        this.id=id;
        this.name=name;
        this.score=score;
        this.date=date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

