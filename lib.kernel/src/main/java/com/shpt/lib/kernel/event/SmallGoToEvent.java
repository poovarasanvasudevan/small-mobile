package com.shpt.lib.kernel.event;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import android.app.Activity;

import net.wequick.small.Small;

import java.util.Map;
import java.util.Set;

/**
 * Created by poovarasanv on 31/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 31/7/17 at 11:50 AM
 */

public class SmallGoToEvent implements EventBase {
    @Override
    public Boolean beforeExecute(Activity context, JsonObject params) {
        return true;
    }

    @Override
    public JsonObject execute(Activity context, JsonObject params) {
        JsonObject extras      = params.get("extras").getAsJsonObject();
        String     activityUrl = params.get("activity").getAsString();

        String activityUrlFormation = activityUrl;

        Set<Map.Entry<String, JsonElement>> entries = extras.entrySet();//will return members of your object
        int                                 i       = 0;
        for (Map.Entry<String, JsonElement> entry : entries) {
            if (i == 0) {
                activityUrlFormation += "?" + entry.getKey() + "=" + entry.getValue().getAsString() + "&";
                i++;
            } else {
                activityUrlFormation += entry.getKey() + "=" + entry.getValue().getAsString() + "&";
                i++;
            }

        }

        Small.openUri(activityUrlFormation, context);
        context.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        return null;
    }

    @Override
    public void afterExecute(Activity context, JsonObject inp, JsonObject op) {

    }
}
