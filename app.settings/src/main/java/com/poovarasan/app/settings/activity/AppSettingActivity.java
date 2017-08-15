package com.poovarasan.app.settings.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.AppUtils;
import com.poovarasan.app.settings.R;
import com.poovarasan.mpreferences.MaterialStandardPreference;
import com.shpt.lib.kernel.Base;

public class AppSettingActivity extends AppCompatActivity {


    private Toolbar                    toolbar;
    private AppBarLayout               appbar;
    private LinearLayout               applicationSettingBar;
    private MaterialStandardPreference appModules;
    private MaterialStandardPreference appVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_setting);
        initView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(Base.getIcon("sli_arrow_left",20, Color.WHITE));

        getSupportActionBar().setTitle("App Settings");

        appModules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Small.openUri("settings/module",getApplicationContext());
                Intent i =new Intent(AppSettingActivity.this,ModuleActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ActivityCompat.startActivity(getApplicationContext(), i, null);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });

        appVersion.setSummary(AppUtils.getAppVersionName());
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        applicationSettingBar = (LinearLayout) findViewById(R.id.applicationSettingBar);
        appModules = (MaterialStandardPreference) findViewById(R.id.app_modules);
        appVersion = (MaterialStandardPreference) findViewById(R.id.app_version);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
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
}
