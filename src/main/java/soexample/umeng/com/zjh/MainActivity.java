package soexample.umeng.com.zjh;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import soexample.umeng.com.zjh.fragment.ShouyeFragment;
import soexample.umeng.com.zjh.fragment.WodeFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton shouye;
    private RadioButton wode;
    private RadioGroup rg;
    private FrameLayout frame;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        manager.beginTransaction().replace(R.id.frame,new ShouyeFragment()).commit();
    }

    private void initView() {
        shouye = findViewById(R.id.shouye);
        wode = findViewById(R.id.wode);
        rg = findViewById(R.id.rg);
        frame = findViewById(R.id.frame);
        manager = getSupportFragmentManager();

        rg.setOnClickListener(this);
        shouye.setOnClickListener(this);
        wode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shouye:
                manager.beginTransaction().replace(R.id.frame,new ShouyeFragment()).commit();
                break;
            case R.id.wode:
                manager.beginTransaction().replace(R.id.frame,new WodeFragment()).commit();
                break;
        }
    }
}
