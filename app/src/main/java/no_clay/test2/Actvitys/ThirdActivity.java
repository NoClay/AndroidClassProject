package no_clay.test2.Actvitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import no_clay.test2.R;

/**
 * Created by no_clay on 2017/3/28.
 */

public class ThirdActivity extends AppCompatActivity {
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.forgetPass)
    Button mForgetPass;
    @BindView(R.id.register)
    Button mRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Android6.0在这里设置并没有什么用
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login, R.id.forgetPass, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                Toast.makeText(this, "准备登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.forgetPass:
                Toast.makeText(this, "忘记密码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.register:
                Toast.makeText(this, "注册帐号", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
