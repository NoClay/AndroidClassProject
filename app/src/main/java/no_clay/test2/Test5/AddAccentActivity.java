package no_clay.test2.Test5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import no_clay.test2.R;
import no_clay.test2.UtilPaskage.MyConstants;

public class AddAccentActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 测试
     */
    private TextView mTitle;
    private ImageView mLeftButton;
    private ImageView mRightButton;
    private EditText mName;
    private EditText mNumber;
    private Spinner mType;
    /**
     * 完成
     */
    private Button mSave;
    private Accent mAccent = new Accent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test5_activity_add_accent);
        initView();
    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.title);
        mLeftButton = (ImageView) findViewById(R.id.leftButton);
        mLeftButton.setOnClickListener(this);
        mRightButton = (ImageView) findViewById(R.id.rightButton);
        mName = (EditText) findViewById(R.id.name);
        mNumber = (EditText) findViewById(R.id.number);
        mType = (Spinner) findViewById(R.id.type);
        mSave = (Button) findViewById(R.id.save);
        mSave.setOnClickListener(this);
        mTitle.setText("添加联系人");
        mLeftButton.setImageResource(R.drawable.back_icon);
        mRightButton.setImageResource(R.drawable.add_icon);
        mRightButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftButton:
                finish();
                break;
            case R.id.save:
                mAccent.setName(mName.getText().toString());
                mAccent.setNumber(mNumber.getText().toString());
                mAccent.setType(mType.getSelectedItem().toString());
                if (mAccent.getName().length() == 0 || mAccent.getNumber().length() == 0){
                    Toast.makeText(this, "格式错误", Toast.LENGTH_SHORT).show();
                }else{
                    MyDataBaseHelper helper = new MyDataBaseHelper(this, MyConstants.LOCAL_DATABASE_NAME,
                            null, MyConstants.DATABASE_VERSION);
                    helper.insertAccent(mAccent);
                    finish();
                }
                break;
        }
    }
}
