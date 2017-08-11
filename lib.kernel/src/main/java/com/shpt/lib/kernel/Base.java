package com.shpt.lib.kernel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mikepenz.iconics.IconicsDrawable;
import com.poovarasan.blade.EventType;
import com.poovarasan.blade.ImageLoaderCallback;
import com.poovarasan.blade.builder.DataAndViewParsingLayoutBuilder;
import com.poovarasan.blade.builder.DataParsingLayoutBuilder;
import com.poovarasan.blade.builder.LayoutBuilderFactory;
import com.poovarasan.blade.module.Module;
import com.poovarasan.blade.toolbox.BitmapLoader;
import com.poovarasan.blade.toolbox.Styles;
import com.poovarasan.bladeappcompat.AppCompatModule;
import com.poovarasan.bladecardview.CardViewModule;
import com.poovarasan.bladedesign.DesignModule;
import com.shpt.lib.kernel.activity.BaseActivity;
import com.shpt.lib.kernel.event.EventHandler;
import com.shpt.lib.kernel.formatter.SessionFormatter;
import com.shpt.lib.kernel.models.ModuleDetail;
import com.shpt.lib.kernel.module.CustomModule;

import net.wequick.small.Small;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * Created by poovarasanv on 21/6/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 21/6/17 at 3:43 PM
 */

public class Base {

    private static Context                 thiscontext;
    private static Map<String, JsonObject> layouts;
    private static BaseActivity            baseActivity;
    public static final String PARCEL = "shpt_parcel";

    public static void initBase(BaseActivity baseActivity1) {
        baseActivity = baseActivity1;
        thiscontext = baseActivity1;
    }

    public static Drawable getIcon(String icon,int size,int color) {
        return new IconicsDrawable(thiscontext,icon).sizeDp(size).color(color);
    }

    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }

    public void buildLayout(View blade, ViewGroup viewGroup, JsonObject layout, JsonObject data, JsonObject styles) {
        blade = (View) getLayoutBuilder().build(viewGroup, layout, data, 0, new Gson().fromJson(styles, Styles.class));
        viewGroup.addView((View) blade);
    }

    public static List<ModuleDetail> getAllModules() {
        Map<String,Integer> modules = Small.getBundleVersions();

        List<ModuleDetail> moduleDetails = new ArrayList<>();
        Set<Map.Entry<String,Integer>> moduleKey = modules.entrySet();
        for(Map.Entry<String,Integer> entry : moduleKey) {
            moduleDetails.add(new ModuleDetail(entry.getKey(),entry.getValue()));
        }

        return moduleDetails;
    }
    public static DataParsingLayoutBuilder getLayoutBuilder() {

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
        dataParsingLayoutBuilder.setListener(new EventHandler(baseActivity));

        //formatter
        dataParsingLayoutBuilder.registerFormatter(new SessionFormatter());

        //  CommunityMaterial.Icon.cmd_magnify

        //bitmap
        dataParsingLayoutBuilder.setBitmapLoader(new BitmapLoader() {
            @Override
            public Future<Bitmap> getBitmap(String imageUrl, View view) {
                return null;
            }

            @Override
            public void getBitmap(String imageUrl, final ImageLoaderCallback imageLoaderCallback, View view, JsonObject layout) {


                Ion.with(thiscontext)
                        .load(imageUrl)
                        .asBitmap()
                        .setCallback(new FutureCallback<Bitmap>() {

                            @Override
                            public void onCompleted(Exception e, Bitmap result) {
                                imageLoaderCallback.onResponse(result);
                            }
                        });
            }
        });

        return dataParsingLayoutBuilder;
    }

    public static JsonParser getJsonParser() {
        return new JsonParser();
    }

    public static Gson getGson() {
        return new Gson();
    }

    public static void toast(String message) {
        Toast.makeText(thiscontext, message, Toast.LENGTH_SHORT).show();
    }

    public static void getAllLayouts() {
        //set Layouts to Layouts

        String json = null;
        try {
            InputStream is     = thiscontext.getAssets().open("alllayouts.json");
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

    public static void fireEvent(View view, EventType eventType, JsonElement jsonElement, Activity activity) {

        try {
            Log.i("MY EVENT", "I am Here 2");
            if (eventType == EventType.OnClick) {
                Log.i("MY EVENT", "I am Here 3");
                JsonObject action      = jsonElement.getAsJsonObject();
                JsonArray  actionItems = action.getAsJsonArray("event");

                for (JsonElement actionItem : actionItems) {
                    JsonObject task = actionItem.getAsJsonObject();
                    Log.i("MY EVENT", "I am Here 4");

                    Class  className = Class.forName(task.get("event").getAsString());
                    Object obj       = className.newInstance();

                    Method beforeExecute = className.getDeclaredMethod("beforeExecute", Activity.class, JsonObject.class);
                    Method afterExecute  = className.getDeclaredMethod("afterExecute", Activity.class, JsonObject.class, JsonObject.class);
                    Method execute       = className.getDeclaredMethod("execute", Activity.class, JsonObject.class);

                    Log.i("MY EVENT", "I am Here 5");
                    Boolean before = (Boolean) beforeExecute.invoke(obj, activity, task);
                    if (before) {
                        JsonObject op = (JsonObject) execute.invoke(obj, activity, task);
                        afterExecute.invoke(obj, activity, task, op);
                    }
                }
            } else {
                Base.toast("Event Type Not Registered");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static JsonObject getGlobalStyles(String fullData) {
        return Base.getJsonParser().parse(fullData).getAsJsonObject().getAsJsonObject("globalStyles");
    }

    public static JsonObject getGlobalData(String jsonObject) {
        return Base.getJsonParser().parse(jsonObject).getAsJsonObject().getAsJsonObject("globalData");
    }

    public static String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is     = thiscontext.getAssets().open("layout.json");
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

}
