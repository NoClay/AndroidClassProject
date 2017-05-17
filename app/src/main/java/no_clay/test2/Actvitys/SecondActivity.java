package no_clay.test2.Actvitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import no_clay.test2.R;

public class SecondActivity extends AppCompatActivity {


    ArrayList<CheckBox> favs = new ArrayList<>();
    private EditText mName;
    private EditText mPassword;
    private RadioGroup mSexGroup;
    private EditText mTel;
    private Spinner mDept;
    /**
     * 书籍
     */
    private CheckBox mBook;
    /**
     * 运动
     */
    private CheckBox mSport;
    /**
     * 音乐
     */
    private CheckBox mMusic;
    /**
     * 电影
     */
    private CheckBox mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ButterKnife.bind(this);
        initFavs();
//        startThird();
    }

    private void startThird() {
        startActivity(new Intent(this, ThirdActivity.class));
    }

    private void initFavs() {
        favs.add(mBook);
        favs.add(mSport);
        favs.add(mMusic);
        favs.add(mMovie);
    }

    public String getSex() {
        RadioButton checked = (RadioButton) findViewById(mSexGroup.getCheckedRadioButtonId());
        return checked.getText().toString();
    }

    public String getFavorite() {
        StringBuilder builder = new StringBuilder();
        for (CheckBox cb : favs) {
            if (cb.isChecked()) {
                builder.append(cb.getText().toString());
                builder.append("、");
            }
        }
        if (builder.length() != 0) {
            return builder.toString().substring(0, builder.length() - 1);
        } else {
            return "您未选择爱好";
        }
    }


    public void myclick(View view) {
        if (checkLogin()) {
            StringBuilder builder = new StringBuilder();
            ;
            builder.append("用户名：" + mName.getText().toString() + "\n");
            builder.append("密码: " + mPassword.getText().toString() + "\n");
            builder.append("性别：" + getSex() + "\n");
            builder.append("电话：" + mTel.getText().toString() + "\n");
            builder.append("部门：" + mDept.getSelectedItem().toString() + "\n");
            builder.append("爱好：" + getFavorite());
            Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("data", builder.toString());
            startActivity(intent);
        }
    }

    private boolean checkLogin() {
        //检查登录
        if (mName.getText().toString().length() <= 6 ||
                mPassword.getText().toString().length() <= 6) {
            Toast.makeText(this, "用户名或密码过短", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initView() {
        mName = (EditText) findViewById(R.id.name);
        mPassword = (EditText) findViewById(R.id.password);
        mSexGroup = (RadioGroup) findViewById(R.id.sexGroup);
        mTel = (EditText) findViewById(R.id.tel);
        mDept = (Spinner) findViewById(R.id.dept);
        mBook = (CheckBox) findViewById(R.id.book);
        mSport = (CheckBox) findViewById(R.id.sport);
        mMusic = (CheckBox) findViewById(R.id.music);
        mMovie = (CheckBox) findViewById(R.id.movie);
    }
}
