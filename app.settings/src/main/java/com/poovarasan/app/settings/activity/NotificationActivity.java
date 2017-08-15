package com.poovarasan.app.settings.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.SnackbarUtils;
import com.poovarasan.app.settings.R;
import com.poovarasan.mpreferences.MaterialSwitchPreference;
import com.shpt.lib.kernel.Base;

public class NotificationActivity extends AppCompatActivity {

    private Toolbar                  toolbar;
    private RelativeLayout           baseView1;
    private AppBarLayout             appbar;
    private LinearLayout             notificationSettingBar;
    private MaterialSwitchPreference productNewSettings;
    private MaterialSwitchPreference productDiscountSettings;
    private MaterialSwitchPreference orderStatusSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(Base.getIcon("sli_arrow_left",20,Color.WHITE));
        getSupportActionBar().setTitle("Notification Settings");


        orderStatusSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SnackbarUtils
                        .with(view)
                        .setMessage(orderStatusSettings.getValue() ? "Order Status Notification Enabled" : "Order Status Notification Disabled")
                        .setMessageColor(Color.WHITE)
                     //   .setBgColor(orderStatusSettings.getValue() ? Color.GREEN : Color.RED)
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        baseView1 = (RelativeLayout) findViewById(R.id.baseView1);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        notificationSettingBar = (LinearLayout) findViewById(R.id.notificationSettingBar);
        productNewSettings = (MaterialSwitchPreference) findViewById(R.id.product_new_settings);
        productDiscountSettings = (MaterialSwitchPreference) findViewById(R.id.product_discount_settings);
        orderStatusSettings = (MaterialSwitchPreference) findViewById(R.id.order_status_settings);
    }
}
