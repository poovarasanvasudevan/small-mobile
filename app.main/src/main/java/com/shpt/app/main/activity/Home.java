package com.shpt.app.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.shpt.app.main.R;
import com.shpt.lib.kernel.activity.BaseActivity;

/**
 * Created by poovarasanv on 7/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 7/7/17 at 10:03 AM
 */

public class Home extends BaseActivity {

    private RelativeLayout baseView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initView();

        Intent intent = getIntent();

        String view = intent.getExtras().getString("view", "home");
        super.render(baseView, view);


        //CommunityMaterial.Icon.cmd_bell_ring

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initView() {
        baseView = (RelativeLayout) findViewById(R.id.baseView);
    }
}
