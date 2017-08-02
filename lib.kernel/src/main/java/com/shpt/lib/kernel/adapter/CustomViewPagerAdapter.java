package com.shpt.lib.kernel.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.poovarasan.blade.toolbox.Styles;
import com.shpt.lib.kernel.Base;
import com.shpt.lib.kernel.R;

/**
 * Created by poovarasanv on 24/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 24/7/17 at 10:59 AM
 */

public class CustomViewPagerAdapter extends PagerAdapter {

    private Context   context;
    private JsonArray layoutJson;


    public CustomViewPagerAdapter(Context context, JsonArray layoutJson) {
        this.context = context;
        this.layoutJson = layoutJson;
    }

    @Override
    public int getCount() {
        return layoutJson.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View           inflateView   = LayoutInflater.from(context).inflate(R.layout.pager_item, container, false);
        RelativeLayout basePagerView = inflateView.findViewById(R.id.basePagerView);

        JsonObject currentPager      = layoutJson.get(position).getAsJsonObject();
        JsonObject currentView       = currentPager.has("view") ? currentPager.get("view").getAsJsonObject() : new JsonObject();
        JsonObject currentData       = currentPager.has("data") ? currentPager.get("data").getAsJsonObject() : new JsonObject();
        JsonObject currentStyledummy = currentPager.has("styles") ? currentPager.get("styles").getAsJsonObject() : new JsonObject();

        String fullJson = Base.readJSONFromAsset();

        currentData.add("globalData", Base.getGlobalData(fullJson));
        currentStyledummy.add("globalStyles", Base.getGlobalStyles(fullJson));

        Log.i("ViewLayout",currentView.toString());

        View bladeView = (View) Base.getLayoutBuilder().build(basePagerView, currentView, currentData, 0, new Gson().fromJson(currentStyledummy, Styles.class));
        basePagerView.addView(bladeView);

        return inflateView;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }

    @Override
    public Parcelable saveState() {
        return super.saveState();
    }
}
