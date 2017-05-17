package no_clay.test2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import no_clay.test2.Actvitys.SecondActivity;
import no_clay.test2.Actvitys.ThirdActivity;
import no_clay.test2.Test4.MainActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 第二次实验
     */
    private TextView mButton2;
    /**
     * 第三次实验
     */
    private TextView mButton3;
    /**
     * 第四次实验
     */
    private TextView mButton4;
    /**
     * 第五次实验
     */
    private TextView mButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initView();
    }

    private void initView() {
        mButton2 = (TextView) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mButton3 = (TextView) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mButton4 = (TextView) findViewById(R.id.button4);
        mButton4.setOnClickListener(this);
        mButton5 = (TextView) findViewById(R.id.button5);
        mButton5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.button2:
                intent = new Intent(this, SecondActivity.class);
                break;
            case R.id.button3:
                intent = new Intent(this, ThirdActivity.class);
                break;
            case R.id.button4:
                intent = new Intent(this, MainActivity.class);
                break;
            case R.id.button5:
                intent = new Intent(this, no_clay.test2.Test5.MainActivity.class);
                break;
        }
        startActivity(intent);
    }
}
