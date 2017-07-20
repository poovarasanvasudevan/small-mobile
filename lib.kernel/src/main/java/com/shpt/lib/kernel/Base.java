package com.shpt.lib.kernel;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.poovarasan.blade.EventType;

import java.lang.reflect.Method;

/**
 * Created by poovarasanv on 21/6/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 21/6/17 at 3:43 PM
 */

public class Base {

    private static Context thiscontext;
    public static final String PARCEL = "shpt_parcel";

    public static void init(Context context) {
        thiscontext = context;
    }

    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }

    public static JsonParser getJsonParser() {
        return new JsonParser();
    }

    public static void toast(String message) {
        Toast.makeText(thiscontext,message,Toast.LENGTH_SHORT).show();
    }

    public static void fireEvent(View view, EventType eventType, JsonElement jsonElement, Activity activity) {

        try {
            Log.i("MY EVENT","I am Here 2");
            if (eventType == EventType.OnClick) {
                Log.i("MY EVENT","I am Here 3");
                JsonObject action      = jsonElement.getAsJsonObject();
                JsonArray  actionItems = action.getAsJsonArray("event");

                for (JsonElement actionItem : actionItems) {
                    JsonObject task = actionItem.getAsJsonObject();
                    Log.i("MY EVENT","I am Here 4");

                    Class className = Class.forName(task.get("event").getAsString());
                    Object obj = className.newInstance();

                    Method beforeExecute = className.getDeclaredMethod("beforeExecute",Activity.class,JsonObject.class);
                    Method afterExecute = className.getDeclaredMethod("afterExecute",Activity.class,JsonObject.class,JsonObject.class);
                    Method execute = className.getDeclaredMethod("execute",Activity.class,JsonObject.class);

                    Log.i("MY EVENT","I am Here 5");
                    Boolean before = (Boolean)beforeExecute.invoke(obj,activity,task);
                    if(before) {
                        JsonObject op = (JsonObject)execute.invoke(obj,activity,task);
                        afterExecute.invoke(obj,activity,task,op);
                    }
                }
            } else {
                Base.toast("Event Type Not Registered");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
