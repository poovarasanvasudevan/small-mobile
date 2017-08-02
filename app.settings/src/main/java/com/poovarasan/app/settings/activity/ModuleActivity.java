package com.poovarasan.app.settings.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.burakeregar.easiestgenericrecycleradapter.base.GenericAdapterBuilder;
import com.burakeregar.easiestgenericrecycleradapter.base.GenericRecyclerAdapter;
import com.poovarasan.app.settings.R;
import com.shpt.lib.kernel.Base;
import com.shpt.lib.kernel.models.ModuleDetail;
import com.shpt.lib.kernel.viewholders.ModuleViewHolder;

import java.util.List;

public class ModuleActivity extends AppCompatActivity {

    private AppBarLayout appbar;
    private Toolbar      toolbar;
    private LinearLayout moduleSettingBar;
    private RecyclerView modulesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        initView();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Modules");

        List<ModuleDetail> appBundles = Base.getAllModules();
        modulesList.setLayoutManager(new LinearLayoutManager(this));

        GenericRecyclerAdapter mAdapter = new GenericAdapterBuilder()
                .addModel(
                        com.shpt.lib.kernel.R.layout.bundles_list, //set your row's layout file
                        ModuleViewHolder.class, //set your view holder class
                        ModuleDetail.class) // set your model class(If you use just String list, it can be just String.class)
                .execute();
        mAdapter.setList(appBundles);
        modulesList.setAdapter(mAdapter);

    }

    private void initView() {
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        moduleSettingBar = (LinearLayout) findViewById(R.id.moduleSettingBar);
        modulesList = (RecyclerView) findViewById(R.id.modulesList);
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
