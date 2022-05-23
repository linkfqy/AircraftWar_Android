package com.hitsz.aircraftwar.dao;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 游戏记录的对象访问接口，表示整个排行榜
 * @author linkfqy
 */
public interface RecordDao extends Serializable {
    /**
     * 添加一条记录
     * @param name 用户名
     * @param score 分数
     * @param date 时间
     */
    void add(String name, int score, Date date);

    /**
     * 由数据结构中的序号获取一条记录
     * @param index 待取记录的序号
     * @return 取到的记录，它在数据结构中的序号是index
     */
    Record getByIndex(int index);

    /**
     * 由id删除一条记录
     * @param recordId 待删除的记录编号
     */
    void deleteById(int recordId);

    /**
     * 整个排行榜按分数降序排名，返回自身的一个引用，便于链式调用
     * @return 已排名的排行榜
     */
    RecordDao getSorted();

    /**
     * 将排行榜转化为行数据列表的形式
     * @return 行数据列表，列表的每个元素为DataGridRow
     */
    List<DataGridRow> toRowList();

    /**
     * 向文件写入RecordDao
     * @param name 待写入的文件名
     */
    void writeToFile(AppCompatActivity activity, String name);
}
