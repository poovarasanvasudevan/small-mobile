package com.shpt.lib.kernel.activity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jayway.jsonpath.JsonPath;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.poovarasan.blade.ImageLoaderCallback;
import com.poovarasan.blade.builder.DataAndViewParsingLayoutBuilder;
import com.poovarasan.blade.builder.DataParsingLayoutBuilder;
import com.poovarasan.blade.builder.LayoutBuilderFactory;
import com.poovarasan.blade.module.Module;
import com.poovarasan.blade.toolbox.BitmapLoader;
import com.poovarasan.blade.toolbox.Styles;
import com.poovarasan.blade.view.BladeView;
import com.poovarasan.bladeappcompat.AppCompatModule;
import com.poovarasan.bladecardview.CardViewModule;
import com.poovarasan.bladedesign.DesignModule;
import com.shpt.lib.kernel.Base;
import com.shpt.lib.kernel.R;
import com.shpt.lib.kernel.event.EventHandler;
import com.shpt.lib.kernel.formatter.SessionFormatter;
import com.shpt.lib.kernel.helper.TransitionHelper;
import com.shpt.lib.kernel.module.CustomModule;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Future;

import static android.R.attr.type;

/**
 * Created by poovarasanv on 21/6/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 21/6/17 at 3:43 PM
 */

public class BaseActivity extends AppCompatActivity {
    BladeView               bladeView;
    Menu                    topMenu;
    JsonArray               topMenuArray;
    Map<String, JsonObject> layouts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(CalligraphyContextWrapper.wrap(newBase)));
        // super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.default_menu, menu);
        topMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (topMenuArray != null) {
            constructTopMenu(menu, topMenuArray);
        }
        return super.onPrepareOptionsMenu(menu);
    }



    @Override
    protected void onStart() {
        super.onStart();
        Base.init(this);
    }

    public void render(ViewGroup viewGroup, JsonObject layout, JsonObject data, JsonObject styles) {
        buildLayout(viewGroup, layout, data, styles);
        setup();
        setupSideBar(layout);
        setupTopMenu();
    }

    public void render(ViewGroup viewGroup, String layoutName) {
        String     completeJson = readJSONFromAsset();
        JsonArray  full1        = JsonPath.read(completeJson, String.format("$.layouts[?(@.name=='%s')]", layoutName));
        JsonObject full         = full1.get(0).getAsJsonObject();
        JsonObject data         = full.getAsJsonObject("data");

        data.add("globalData", getGlobalData(completeJson));

        JsonObject styles = full.getAsJsonObject("styles");
        styles.add("globalStyles", getGlobalStyles(completeJson));

        buildLayout(viewGroup, full.getAsJsonObject("view"), data, styles);

        setup();
        setupSideBar(full);
        setupTopMenu();
    }

    public void setupTopMenu() {
        invalidateOptionsMenu();
    }

    public void setupSideBar(JsonObject layoutData) {
        final DrawerLayout drawerLayout   = (DrawerLayout) find("drawer");
        NavigationView     navigationView = (NavigationView) find("navigation_view");
        Toolbar            toolbar        = (Toolbar) find("toolbar");
        if (drawerLayout != null && navigationView != null) {

            DrawerLayout.LayoutParams lp = new DrawerLayout.LayoutParams(
                    DrawerLayout.LayoutParams.WRAP_CONTENT, DrawerLayout.LayoutParams.MATCH_PARENT, GravityCompat.START);

            lp.gravity = Gravity.START;
            navigationView.setLayoutParams(lp);


            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    drawerLayout.closeDrawers();
                    return true;
                }
            });


            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

                @Override
                public void onDrawerClosed(View drawerView) {
                    // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                    super.onDrawerClosed(drawerView);
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                    super.onDrawerOpened(drawerView);
                }
            };


            drawerLayout.setDrawerListener(actionBarDrawerToggle);

            //calling sync state is necessay or else your hamburger icon wont show up
            actionBarDrawerToggle.syncState();


            if (layoutData.has("config") && layoutData.has("menus")) {
                JsonObject menu   = layoutData.getAsJsonObject("menus").getAsJsonObject();
                JsonObject config = layoutData.get("config").getAsJsonObject();

                if (config.has("topMenu")) {

                    String    topBarMenuId = config.get("topMenu").getAsString();
                    JsonArray topBarMenu   = menu.get(topBarMenuId).getAsJsonArray();
                    topMenuArray = topBarMenu;
                    constructTopMenu(topMenu, topBarMenu);
                }

                if (config.has("sideBarMenu")) {
                    String    sideBarMenuId = config.get("sideBarMenu").getAsString();
                    JsonArray sideBarMenu   = menu.get(sideBarMenuId).getAsJsonArray();

                    constructSidebarMenu(navigationView.getMenu(), sideBarMenu);
                }
            }
        }

    }

    public void constructTopMenu(Menu topMenu, JsonArray menuArray) {
        if (topMenu != null) {

            Log.i("BASE", "topMenu Is Here");
            topMenu.clear();

            for (JsonElement menuObj : menuArray) {
                JsonObject menuObjEx = menuObj.getAsJsonObject();
                MenuItem   menuItem  = topMenu.add(menuObjEx.has("title") ? menuObjEx.get("title").getAsString() : "Unknown");

                if (menuObjEx.has("visibility")) {
                    menuItem.setVisible(Boolean.parseBoolean(menuObjEx.get("visibility").getAsString()));
                }

                if (menuObjEx.has("icon")) {
                    menuItem.setIcon(new IconicsDrawable(getApplicationContext(), menuObjEx.get("icon").getAsString()).actionBar().color(Color.WHITE));
                }

                if (menuObjEx.has("showaction")) {
                    if (menuObjEx.get("showaction").getAsString().equalsIgnoreCase("always")) {
                        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                    } else if (menuObjEx.get("showaction").getAsString().equalsIgnoreCase("if_room")) {
                        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                    } else if (menuObjEx.get("showaction").getAsString().equalsIgnoreCase("never")) {
                        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                    } else {
                        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                    }
                } else {
                    menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                }

                if(menuObjEx.has("badge") && menuObjEx.has("icon")) {
                    ActionItemBadge.update(this, menuItem, new IconicsDrawable(getApplicationContext(), menuObjEx.get("icon").getAsString()).actionBar().color(Color.WHITE), ActionItemBadge.BadgeStyles.GREEN, menuObjEx.get("badge").getAsString());
                }

            }
        }
    }

    public void constructSidebarMenu(Menu menu, JsonArray menuArray) {
        Random r = new Random();
        menu.clear();
        for (JsonElement menuObj : menuArray) {
            JsonObject menuObjEx = menuObj.getAsJsonObject();
            if (menuObjEx.has("children")) {

                SubMenu   subMenu  = menu.addSubMenu(menuObjEx.has("title") ? menuObjEx.get("title").getAsString() : "Unknown");
                JsonArray children = menuObjEx.get("children").getAsJsonArray();
                for (JsonElement childrenMenu : children) {
                    JsonObject childrenMenuEx = childrenMenu.getAsJsonObject();
                    MenuItem   menuItem       = subMenu.add(childrenMenuEx.has("title") ? childrenMenuEx.get("title").getAsString() : "Unknown");
                    if (childrenMenuEx.has("visibility")) {
                        menuItem.setVisible(Boolean.parseBoolean(childrenMenuEx.get("visibility").getAsString()));
                    }

                    if (childrenMenuEx.has("icon")) {
                        menuItem.setIcon(new IconicsDrawable(getApplicationContext(), childrenMenuEx.get("icon").getAsString()).actionBar());
                    }

                    //FontAwesome.Icon.faw_bell
                    //Pixeden7Stroke.Icon.pe7_7s_cart

                }
            } else {
                MenuItem menuItem = menu.add(menuObjEx.has("title") ? menuObjEx.get("title").getAsString() : "Unknown");

                if (menuObjEx.has("visibility")) {
                    menuItem.setVisible(Boolean.parseBoolean(menuObjEx.get("visibility").getAsString()));
                }

                if (menuObjEx.has("icon")) {
                    menuItem.setIcon(new IconicsDrawable(getApplicationContext(), menuObjEx.get("icon").getAsString()).actionBar());
                }

            }
            // String title =

        }
    }

    public void buildLayout(ViewGroup viewGroup, JsonObject layout, JsonObject data, JsonObject styles) {
        bladeView = getLayoutBuilder().build(viewGroup, layout, data, 0, new Gson().fromJson(styles, Styles.class));
        viewGroup.addView((View) bladeView);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @SuppressWarnings("unchecked")
    public void transitionTo(Intent i, boolean finish) {
        final Pair<View, String>[] pairs                     = TransitionHelper.createSafeTransitionParticipants(this, true);
        ActivityOptionsCompat      transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(i, transitionActivityOptions.toBundle());
    }

    protected JsonObject getGlobalStyles(String fullData) {
        return Base.getJsonParser().parse(fullData).getAsJsonObject().getAsJsonObject("globalStyles");
    }

    protected JsonObject getGlobalData(String jsonObject) {
        return Base.getJsonParser().parse(jsonObject).getAsJsonObject().getAsJsonObject("globalData");
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
        super.onBackPressed();
    }

    static final int TYPE_PROGRAMMATICALLY = 0;
    static final int TYPE_XML              = 1;

    private void setupWindowAnimations() {
        android.transition.Transition transition;

        if (type == TYPE_PROGRAMMATICALLY) {
            transition = buildEnterTransition();
        } else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        }
        getWindow().setEnterTransition(transition);
    }


    private android.transition.Transition buildEnterTransition() {
        Explode enterTransition = new Explode();
        enterTransition.setDuration(200);
        return enterTransition;
    }


    protected String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is     = getAssets().open("layout.json");
            int         size   = is.available();
            byte[]      buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        Log.i("Main Module", json);
        return json;
    }

    public View find(String id) {
        return findViewById(bladeView.getViewManager().getUniqueViewId(id));
    }

    public void setup() {
        Toolbar toolbar = (Toolbar) find("toolbar");

        if (toolbar != null) {
            toolbar.getContext().setTheme(R.style.ThemeOverlay_AppCompat_Dark_ActionBar);
            setSupportActionBar(toolbar);
        }
    }


    public void getAllLayouts() {
        //set Layouts to Layouts

        String json = null;
        try {
            InputStream is     = getAssets().open("alllayouts.json");
            int         size   = is.available();
            byte[]      buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        Log.i("Main All  Layouts", json);
        Type type = new TypeToken<Map<String, JsonObject>>() {
        }.getType();
        layouts = new Gson().fromJson(json, type);

    }

    BitmapLoader bitmapLoader = new BitmapLoader() {
        @Override
        public Future<Bitmap> getBitmap(String imageUrl, View view) {
            return null;
        }

        @Override
        public void getBitmap(String imageUrl, final ImageLoaderCallback imageLoaderCallback, View view, JsonObject layout) {
            Glide.with(getApplicationContext())
                    .load(imageUrl)
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            imageLoaderCallback.onResponse(resource);
                        }
                    });
        }
    };

    public DataParsingLayoutBuilder getLayoutBuilder() {

        getAllLayouts();

        DataAndViewParsingLayoutBuilder dataParsingLayoutBuilder = new LayoutBuilderFactory().getDataAndViewParsingLayoutBuilder(layouts);

        //modules
        Module appcompatModule = new AppCompatModule();
        appcompatModule.register(dataParsingLayoutBuilder);

        Module cardviewModule = new CardViewModule();
        cardviewModule.register(dataParsingLayoutBuilder);

        Module designModule = new DesignModule();
        designModule.register(dataParsingLayoutBuilder);

        Module customModule = new CustomModule();
        customModule.register(dataParsingLayoutBuilder);

        //base layout
        dataParsingLayoutBuilder.setLayouts(layouts);

        //listener
        dataParsingLayoutBuilder.setListener(new EventHandler(this));

        //formatter
        dataParsingLayoutBuilder.registerFormatter(new SessionFormatter());

      //  CommunityMaterial.Icon.cmd_magnify

        //bitmap
        dataParsingLayoutBuilder.setBitmapLoader(bitmapLoader);

        return dataParsingLayoutBuilder;
    }
}
