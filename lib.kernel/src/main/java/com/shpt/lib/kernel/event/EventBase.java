package com.shpt.lib.kernel.event;

import com.google.gson.JsonObject;

import android.app.Activity;

/**
 * Created by poovarasanv on 20/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 20/7/17 at 11:52 AM
 */

public interface EventBase {
    public Boolean beforeExecute(Activity context, JsonObject params);

    public JsonObject execute(Activity context, JsonObject params);

    public void afterExecute(Activity context, JsonObject inp, JsonObject op);
}
