package com.hitsz.aircraftwar.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * RecordDao的一个实现
 * @author linkfqy
 */
public class RecordDaoImpl implements RecordDao, Serializable {
    private final List<Record> records;
    private int maxId;
    public RecordDaoImpl(){
        records=new ArrayList<>();
        maxId=0;
    }

    @Override
    public void add(String name, int score, Date date) {
        records.add(new Record(++maxId,name,score,date));
    }

    /**
     * 获取在List中序号（从0开始）为index的记录
     * @param index 待取记录的序号
     * @return 取得的记录，其序号是index
     */
    @Override
    public Record getByIndex(int index) {
        return records.get(index);
    }

    @Override
    public void deleteById(int recordId) {
        records.removeIf(r -> r.getId()==recordId);
    }

    @Override
    public RecordDao getSorted() {
        records.sort(Comparator.comparing(Record::getScore).reversed());
        return this;
    }

    @SuppressLint("SimpleDateFormat")
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public List<DataGridRow> toRowList() {
        List<DataGridRow> result= new LinkedList<>();
        int i=1;
        for (Record record:records){
            result.add(new DataGridRow(i++,
                                       record.getName(),
                                       record.getScore(),
                                       sdf.format(record.getDate())));
        }
        return result;
    }

    /**
     * 从文件读取RecordDaoImpl
     * @param name 待读取的文件名
     * @return 读取到的RecordDaoImpl
     */
    public static RecordDaoImpl readFromFile(AppCompatActivity activity,String name){
        RecordDaoImpl result = null;
        try(FileInputStream fis=activity.openFileInput(name);
            ObjectInputStream ois=new ObjectInputStream(fis)) {
            result=(RecordDaoImpl) ois.readObject();
        }catch (FileNotFoundException|InvalidClassException e){
            result=new RecordDaoImpl();
        }catch (Exception e){
            Log.e("outtag", "readFromFile: fuuuuuuuuuuuuuuuuuuuck");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 向文件写入RecordDaoImpl
     * @param name 待写入的文件路径
     */
    @Override
    public void writeToFile(AppCompatActivity activity, String name){
        try(FileOutputStream fos=activity.openFileOutput(name, Context.MODE_PRIVATE);
            ObjectOutputStream oos=new ObjectOutputStream(fos);) {
            oos.writeObject(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
