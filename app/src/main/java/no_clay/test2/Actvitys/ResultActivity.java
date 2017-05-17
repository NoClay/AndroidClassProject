package no_clay.test2.Actvitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import no_clay.test2.R;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.show)
    TextView mShow;
    @BindView(R.id.activity_result)
    RelativeLayout mActivityResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        initShow();
    }

    private void initShow() {
        String data = getIntent().getStringExtra("data");
        mShow.setText(data);
    }
}
