package no_clay.test2.Test4;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import no_clay.test2.R;
import no_clay.test2.UtilPaskage.MyConstants;
import no_clay.test2.UtilPaskage.UtilClass;

/**
 * Created by noclay on 2017/5/17.
 */

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 测试
     */
    private TextView mTitle;
    private ImageView mLeftButton;
    private ImageView mRightButton;
    private TextView mDate;
    private TextView mStartTime;
    private TextView mEndTime;
    private EditText mTaskContent;
    private Button mSave;
    private Task mTask;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test4_add_new_task);
        initView();
    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.title);
        mLeftButton = (ImageView) findViewById(R.id.leftButton);
        mLeftButton.setOnClickListener(this);
        mRightButton = (ImageView) findViewById(R.id.rightButton);
        mRightButton.setOnClickListener(this);
        mDate = (TextView) findViewById(R.id.date);
        mDate.setOnClickListener(this);
        mStartTime = (TextView) findViewById(R.id.startTime);
        mStartTime.setOnClickListener(this);
        mEndTime = (TextView) findViewById(R.id.endTime);
        mEndTime.setOnClickListener(this);
        mTaskContent = (EditText) findViewById(R.id.taskContent);
        mSave = (Button) findViewById(R.id.save);
        mSave.setOnClickListener(this);
        mTitle.setText("新建活动");
        mLeftButton.setImageResource(R.drawable.back_icon);
        mRightButton.setVisibility(View.INVISIBLE);
        mRightButton.setImageResource(R.drawable.add_icon);
        mTask = new Task();
        mContext = this;
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        switch (v.getId()) {
            case R.id.leftButton:
                finish();
                break;
            case R.id.rightButton:
                break;
            case R.id.date:
                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mTask.setDate(year + "-" + month + "-" + dayOfMonth);
                                mDate.setText(mTask.getDate());
                            }
                        }, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case R.id.startTime:
                TimePickerDialog startTimePicker = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mTask.setStartTime(hourOfDay + ":" + minute);
                        mStartTime.setText(mTask.getStartTime());
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true);
                startTimePicker.show();
                break;
            case R.id.endTime:
                TimePickerDialog endTimePicker = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mTask.setEndTime(hourOfDay + ":" + minute);
                        mEndTime.setText(mTask.getEndTime());
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true);
                endTimePicker.show();
                break;
            case R.id.save:
                saveToFile();
                break;
        }
    }

    private void saveToFile() {
        if (mTask.getDate() == null || mTask.getStartTime() == null || mTask.getEndTime() == null){
            Toast.makeText(mContext, "没有填写完整的信息", Toast.LENGTH_SHORT).show();
            return;
        }
        File file = new File(MyConstants.TASK_FILE_PATH);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (file.exists() && file.isFile()){
            mTask.setTaskContent(mTaskContent.getText().toString());
            List<Task> list = (List<Task>) UtilClass.readObjectFromFile(file);
            if (list == null){
                list = new ArrayList<>();
            }
            list.add(mTask);
            UtilClass.writeObjectToFile(file, list);
            finish();
        }
    }
}
