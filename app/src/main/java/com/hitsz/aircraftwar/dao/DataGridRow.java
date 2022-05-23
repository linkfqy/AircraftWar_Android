package com.hitsz.aircraftwar.dao;

/**
 * 为满足DataGridView的格式需求而创造的类
 * 每个对象表示表格中的一行
 * @author linkfqy
 */
public class DataGridRow {
    private final int ranking;
    private final String name;
    private final int score;
    private final String time;
    public DataGridRow (int rk,String nm,int sc,String tm){
        ranking=rk;
        name=nm;
        score=sc;
        time=tm;
    }
}
