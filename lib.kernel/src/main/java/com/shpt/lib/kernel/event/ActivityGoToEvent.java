package com.shpt.lib.kernel.event;

import com.google.gson.JsonObject;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;

import com.shpt.lib.kernel.Base;
import com.shpt.lib.kernel.helper.TransitionHelper;

/**
 * Created by poovarasanv on 20/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 20/7/17 at 11:54 AM
 */
public class ActivityGoToEvent implements EventBase {
    @Override
    public Boolean beforeExecute(Activity context, JsonObject params) {
        return true;
    }

    @Override
    public JsonObject execute(Activity context, JsonObject params) {

        Log.i("MY EVENT", "I am Here 6");
        try {
            Class  activityName = Class.forName(params.get("activity").getAsString());
            Intent intent       = new Intent(context, activityName);
            intent.putExtra("view", params.get("view").getAsString());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            if (params.has("data")) {
                intent.putExtra(Base.PARCEL, params.get("data").toString());
            }
            final Pair<View, String>[] pairs                     = TransitionHelper.createSafeTransitionParticipants(context, true);
            ActivityOptionsCompat      transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(context, pairs);

            context.startActivity(intent, transitionActivityOptions.toBundle());

            // context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


        } catch (Exception e) {
            Base.toast("Oops : " + e.getCause().toString());
            //Log.i("MY EVENT","I am Here 6");
        }
        return new JsonObject();
    }

    @Override
    public void afterExecute(Activity context, JsonObject inp, JsonObject op) {

    }
}
