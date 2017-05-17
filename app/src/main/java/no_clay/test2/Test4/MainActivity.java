package no_clay.test2.Test4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no_clay.test2.R;
import no_clay.test2.UtilPaskage.MyConstants;
import no_clay.test2.UtilPaskage.UtilClass;

/**
 * Created by noclay on 2017/5/17.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 测试
     */
    private TextView mTitle;
    private ImageView mLeftButton;
    private ImageView mRightButton;
    private ListView mListView;
    private SimpleAdapter mSimpleAdapter;
    private List<Map<String, String>> resultList;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test4_activity_main);
        initView();
        //读取数据
        File dir = new File(MyConstants.ROOT_PATH);
        if (!dir.exists()){
            dir.mkdir();
        }
        getData();
        mSimpleAdapter = new SimpleAdapter(this, resultList, android.R.layout.simple_list_item_1,
                new String[]{"data"}, new int[]{android.R.id.text1});
        mListView.setAdapter(mSimpleAdapter);
    }


    private void getData(){
        File file = new File(MyConstants.TASK_FILE_PATH);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "getData: ", e);
            }
        }
        if (file.exists() && file.isFile()){
            List<Task> list = (List<Task>) UtilClass.readObjectFromFile(file);
            if (list == null){
                list = new ArrayList<>();
            }
            if (resultList != null){
                resultList.clear();
            }else{
                resultList = new ArrayList<>();
            }
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> temp = new HashMap<>();
                Task task = list.get(i);
                temp.put("data", task.getDate() + "  " + task.getStartTime() + "--" + task.getEndTime() + "  " + task.getTaskContent());
                resultList.add(temp);
            }
        }
    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.title);
        mLeftButton = (ImageView) findViewById(R.id.leftButton);
        mLeftButton.setOnClickListener(this);
        mRightButton = (ImageView) findViewById(R.id.rightButton);
        mRightButton.setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.listView);
        mTitle.setText("活动列表");
        mLeftButton.setImageResource(R.drawable.back_icon);
        mRightButton.setImageResource(R.drawable.add_icon);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftButton:
                finish();
                break;
            case R.id.rightButton:
                Intent intent = new Intent(this, AddTaskActivity.class);
                startActivityForResult(intent, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getData();
//        mSimpleAdapter.notify();
        mSimpleAdapter.notifyDataSetChanged();
    }
}
