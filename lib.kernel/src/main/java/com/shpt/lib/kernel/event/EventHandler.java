package com.shpt.lib.kernel.event;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;

import com.poovarasan.blade.EventType;
import com.poovarasan.blade.builder.LayoutBuilderCallback;
import com.poovarasan.blade.toolbox.Styles;
import com.poovarasan.blade.view.BladeView;
import com.shpt.lib.kernel.Base;
import com.shpt.lib.kernel.activity.BaseActivity;

import java.util.List;

/**
 * Created by poovarasanv on 5/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 5/7/17 at 8:58 AM
 */

public class EventHandler implements LayoutBuilderCallback {

    private BaseActivity activity;

    public EventHandler(BaseActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onUnknownAttribute(String attribute, JsonElement value, BladeView view) {

    }

    @Nullable
    @Override
    public BladeView onUnknownViewType(String type, View parent, JsonObject layout, JsonObject data, int index, Styles styles) {
        return null;
    }

    @Override
    public JsonObject onLayoutRequired(String type, BladeView parent) {
        return null;
    }

    @Override
    public void onViewBuiltFromViewProvider(BladeView view, View parent, String type, int index) {

    }

    @Override
    public View onEvent(BladeView view, JsonElement value, EventType eventType) {

        Log.i("MY EVENT","I am Here 1");
        Base.fireEvent((View) view, eventType, value,activity);
        return (View)view;
    }

    @Override
    public PagerAdapter onPagerAdapterRequired(BladeView parent, List<BladeView> children, JsonObject layout) {
        return null;
    }

    @Override
    public Adapter onAdapterRequired(BladeView parent, List<BladeView> children, JsonObject layout) {
        return null;
    }
}
