package com.hitsz.aircraftwar.application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.hitsz.aircraftwar.R;
import com.hitsz.aircraftwar.dao.DataGridRow;
import com.hitsz.aircraftwar.dao.RecordDao;
import com.hitsz.aircraftwar.dao.RecordDaoImpl;
import com.lingber.mycontrol.datagridview.DataGridView;

import android.os.Bundle;
import android.text.InputFilter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class RankingActivity extends AppCompatActivity {

    private RecordDao recordDao;
    private DataGridView<DataGridRow> mDataGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        // 读取记录
        recordDao = RecordDaoImpl.readFromFile(this,OfflineActivity.gameMode.getFileName());

        mDataGridView = findViewById(R.id.data_gridview);
        // 设置列数
        mDataGridView.setColunms(4);
        // 设置表头内容
        mDataGridView.setHeaderContentByStringId(new int[]{R.string.ranking,R.string.player_name,R.string.score,R.string.record_time});
        // 绑定字段
        mDataGridView.setFieldNames(new String[]{"ranking","name","score","time"});
        // 每个column占比
        mDataGridView.setColunmWeight(new float[]{1.5f,3,2,3});
        // 每个单元格包含控件
        mDataGridView.setCellContentView(new Class[]{TextView.class, TextView.class, TextView.class, TextView.class});
        // 设置数据源
        mDataGridView.setDataSource(recordDao.toRowList());
        // 单行选中模式
        mDataGridView.setSelectedMode(1);
        // 设置可滑动
        mDataGridView.setSlidable(true);
        // 设置行高、表头高
        mDataGridView.setRowHeight(200);
        mDataGridView.setHeaderHeight(100);
        // 初始化表格
        mDataGridView.initDataGridView();
        // 注册点击监听器，点击时删除行
        mDataGridView.setOnItemCellClickListener((v, row, column) -> delRow(row));

        inputName();
    }

    private void inputName() {
        EditText input = new EditText(this);
        input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("请输入名字以记录得分")
                .setView(input)
                .setNegativeButton("取消", null);
        builder.setPositiveButton("确定", (dialogInterface, i) -> {
            String name = input.getText().toString();
            if (!name.isEmpty()) {
                recordDao.add(name,getIntent().getIntExtra("score",0),new Date());
                recordDao.getSorted();
                recordDao.writeToFile(this,OfflineActivity.gameMode.getFileName());
                mDataGridView.setDataSource(recordDao.toRowList());
                mDataGridView.updateAll();
            } else {
                Toast.makeText(this, "签名为空", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    public void delRow(int position) {
        int rank = position + 1;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("是否确认删除第" + rank +"名？")
                .setNegativeButton("取消", null);
        builder.setPositiveButton("确定", (dialogInterface, i) -> {
            recordDao.deleteById(recordDao.getByIndex(position).getId());
            recordDao.writeToFile(this,OfflineActivity.gameMode.getFileName());
            mDataGridView.setDataSource(recordDao.toRowList());
            mDataGridView.updateAll();
        });
        builder.show();
    }

}