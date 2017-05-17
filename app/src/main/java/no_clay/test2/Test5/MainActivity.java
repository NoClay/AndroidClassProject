package no_clay.test2.Test5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no_clay.test2.R;
import no_clay.test2.UtilPaskage.MyConstants;

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
    private List<Accent> mAccents;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test4_activity_main);
        initView();
        //读取数据
        getData();
        mSimpleAdapter = new SimpleAdapter(this, resultList, android.R.layout.simple_list_item_1,
                new String[]{"data"}, new int[]{android.R.id.text1});
        mListView.setAdapter(mSimpleAdapter);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();

                final Accent accent = mAccents.get(position);
                dialog.setMessage(accent.getName() + "\n" + accent.getNumber());
                dialog.setTitle("是否删除？");
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyDataBaseHelper helper = new MyDataBaseHelper(MainActivity.this, MyConstants.LOCAL_DATABASE_NAME,
                                null, MyConstants.DATABASE_VERSION);
                        helper.deleteAccent(accent.getNumber());
                        getData();
                        mSimpleAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return false;
            }
        });
    }


    private void getData(){
        MyDataBaseHelper helper = new MyDataBaseHelper(this, MyConstants.LOCAL_DATABASE_NAME,
                null, MyConstants.DATABASE_VERSION);
        mAccents = helper.getAccent();
        if (resultList != null){
            resultList.clear();
        }else{
            resultList = new ArrayList<>();
        }
        for (int i = 0; i < mAccents.size(); i++) {
            Accent accent = mAccents.get(i);
            Map<String, String> temp = new HashMap<>();
            temp.put("data", accent.getName() + "\t"
            + accent.getNumber() + "\t" + accent.getType());
            resultList.add(temp);
        }
    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.title);
        mLeftButton = (ImageView) findViewById(R.id.leftButton);
        mLeftButton.setOnClickListener(this);
        mRightButton = (ImageView) findViewById(R.id.rightButton);
        mRightButton.setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.listView);
        mTitle.setText("通讯录");
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
                Intent intent = new Intent(this, AddAccentActivity.class);
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