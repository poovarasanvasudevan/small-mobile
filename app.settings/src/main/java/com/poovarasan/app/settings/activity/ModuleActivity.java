package com.poovarasan.app.settings.activity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.burakeregar.easiestgenericrecycleradapter.base.GenericAdapterBuilder;
import com.burakeregar.easiestgenericrecycleradapter.base.GenericRecyclerAdapter;
import com.jakewharton.processphoenix.ProcessPhoenix;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.poovarasan.app.settings.R;
import com.shpt.lib.kernel.Base;
import com.shpt.lib.kernel.models.ModuleDetail;
import com.shpt.lib.kernel.viewholders.ModuleViewHolder;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

import net.wequick.small.Small;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModuleActivity extends AppCompatActivity {

    private AppBarLayout appbar;
    private Toolbar      toolbar;
    private LinearLayout moduleSettingBar;
    private RecyclerView modulesList;
    private CardView     updateCard;
    private ProgressBar  myProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        initView();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(Base.getIcon("sli_arrow_left", 20, Color.WHITE));

        getSupportActionBar().setTitle("Modules");

        List<ModuleDetail> appBundles = Base.getAllModules();
        modulesList.setLayoutManager(new LinearLayoutManager(this));

        GenericRecyclerAdapter mAdapter = new GenericAdapterBuilder()
                .addModel(
                        com.shpt.lib.kernel.R.layout.bundles_list, //set your row's layout file
                        ModuleViewHolder.class, //set your view holder class
                        ModuleDetail.class
                ) // set your model class(If you use just String list, it can be just String.class)
                .execute();
        mAdapter.setList(appBundles);
        modulesList.setAdapter(mAdapter);


        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TransitionSet set = new TransitionSet()
                        .addTransition(new Slide(Gravity.TOP));

                TransitionManager.beginDelayedTransition(updateCard, set);
                updateCard.setVisibility(View.GONE);
            }
        }, 8000);

        if (NetworkUtils.isConnected()) {
            new UpgradeManager(this).checkUpgrade();
        } else {
            ToastUtils.showLongSafe("Update Check Failed due to Network Issue.");
        }

    }

    private void initView() {
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        moduleSettingBar = (LinearLayout) findViewById(R.id.moduleSettingBar);
        modulesList = (RecyclerView) findViewById(R.id.modulesList);
        updateCard = (CardView) findViewById(R.id.updateCard);
        myProgressBar = (ProgressBar) findViewById(R.id.my_progressBar);
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


    public static class UpgradeManager {

        private static class UpdateInfo {
            public String  packageName;
            public Integer versionName;
            public String  downloadUrl;
        }

        private static class UpgradeInfo {
            public JsonObject       manifest;
            public List<UpdateInfo> updates;
        }

        private interface OnResponseListener {
            void onResponse(UpgradeInfo info);
        }

        private interface OnUpgradeListener {
            void onUpgrade(boolean succeed);
        }

        private static class ResponseHandler extends android.os.Handler {
            private OnResponseListener mListener;

            public ResponseHandler(OnResponseListener listener) {
                mListener = listener;
            }

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        mListener.onResponse((UpgradeInfo) msg.obj);
                        break;
                }
            }
        }

        private ResponseHandler mResponseHandler;

        private Context        mContext;
        private MaterialDialog mProgressDlg;
        private MaterialDialog.Builder mbuilder;

        UpgradeManager(Context context) {
            mContext = context;
        }

        void checkUpgrade() {

            mbuilder = new MaterialDialog.Builder(mContext)
                    .content("Checking for updates...")
                    .progress(true, 0)
                    .canceledOnTouchOutside(false);
            mProgressDlg  = mbuilder.show();
            requestUpgradeInfo(Small.getBundleVersions(), new OnResponseListener() {
                @Override
                public void onResponse(UpgradeInfo info) {
                    mProgressDlg.setContent("Upgrading...");
                    upgradeBundles(info,
                            new OnUpgradeListener() {
                                @Override
                                public void onUpgrade(boolean succeed) {
                                    mProgressDlg.dismiss();
                                    mProgressDlg = null;

                                    if (succeed) {

                                        AppUtils.cleanAppData(
                                                mContext.getDataDir(),
                                                mContext.getCacheDir()
                                        );

                                        MaterialDialog dialog = new MaterialDialog.Builder(mContext)
                                                .title("Success")
                                                .content("Upgrade Success! Restart your application to see upgrade changes.")
                                                .positiveText("Restart")
                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                    @Override
                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                        dialog.dismiss();
                                                        ProcessPhoenix.triggerRebirth(mContext);
                                                    }
                                                })
                                                .negativeText("Not now")
                                                .onNegative(new MaterialDialog.SingleButtonCallback() {
                                                    @Override
                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                        dialog.dismiss();
                                                    }
                                                })
                                                .show();

                                    } else {
                                        Toast.makeText(mContext, "Update Failed", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });
                }
            });
        }

        private void requestUpgradeInfo(final Map<String, Integer> versions, OnResponseListener listener) {
            System.out.println(versions); // this should be passed as HTTP parameters
            mResponseHandler = new ResponseHandler(listener);

            try {
                Ion.with(mContext)
                        .load("http://wequick.github.io/small/upgrade/bundles.json")
                        .noCache()
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (e == null) {
                                    JsonObject       manifest = result.has("manifest") ? result.getAsJsonObject("manifest") : null;
                                    JsonArray        updates  = result.getAsJsonArray("updates");
                                    int              N        = updates.size();
                                    List<UpdateInfo> infos    = new ArrayList<UpdateInfo>(N);

                                    mProgressDlg.setContent("Getting the Update Packages");
                                    for (int i = 0; i < N; i++) {
                                        JsonObject o       = updates.get(i).getAsJsonObject();
                                        int        version = o.has("version") ? o.get("version").getAsInt() : 0;

                                        if (version > versions.get(o.get("pkg").getAsString())) {

                                            UpdateInfo updateInfo = new UpdateInfo();
                                            updateInfo.packageName = o.get("pkg").getAsString();
                                            updateInfo.downloadUrl = o.get("url").getAsString();
                                            updateInfo.versionName = o.has("version") ? o.get("version").getAsInt() : 0;

                                            infos.add(updateInfo);
                                        }
                                    }

                                    UpgradeInfo ui = new UpgradeInfo();
                                    ui.manifest = manifest;
                                    ui.updates = infos;
                                    Message.obtain(mResponseHandler, 1, ui).sendToTarget();

                                } else {
                                    mProgressDlg.dismiss();
                                    ToastUtils.showLongSafe(e.getLocalizedMessage());
                                }
                            }
                        });
            } catch (Exception e) {
                ToastUtils.showLongSafe(e.getLocalizedMessage());
            }
        }

        private static class DownloadHandler extends android.os.Handler {
            private OnUpgradeListener mListener;

            DownloadHandler(OnUpgradeListener listener) {
                mListener = listener;
            }

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        mListener.onUpgrade((Boolean) msg.obj);
                        break;
                }
            }
        }

        private DownloadHandler mHandler;

        private void upgradeBundles(final UpgradeInfo info,
                                    final OnUpgradeListener listener) {
            // Just for example, you can do this by OkHttp or something.
            mHandler = new DownloadHandler(listener);

            try {
                // Update manifest
                if (info.manifest != null) {
                    if (!Small.updateManifest(new JSONObject(info.manifest.toString()), false)) {
                        Message.obtain(mHandler, 1, false).sendToTarget();
                        return;
                    }
                }
                // Download bundles
                List<UpdateInfo> updates = info.updates;
                for (final UpdateInfo u : updates) {
                    final net.wequick.small.Bundle bundle = Small.getBundle(u.packageName);
                    File                           file   = bundle.getPatchFile();

                    Ion.with(mContext)
                            .load(u.downloadUrl)
                            .noCache()
                            .write(file)
                            .setCallback(new FutureCallback<File>() {
                                @Override
                                public void onCompleted(Exception e, File result) {
                                    mProgressDlg.setContent("Upgrading " + u.packageName);
                                    bundle.upgrade();
                                }
                            });
                }

                Message.obtain(mHandler, 1, true).sendToTarget();
            } catch (Exception e) {
                e.printStackTrace();
                Message.obtain(mHandler, 1, false).sendToTarget();
            }
        }
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
