package com.shpt.app.main;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.shpt.lib.kernel.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    private RelativeLayout baseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        render(baseView,"main");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Main Module", "PING");
    }

    protected void initView() {
        baseView = findViewById(R.id.baseView);
    }
}
