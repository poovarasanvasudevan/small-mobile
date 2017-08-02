package com.shpt.lib.kernel.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import android.view.ViewGroup;

import com.poovarasan.blade.parser.Attributes;
import com.poovarasan.blade.parser.Parser;
import com.poovarasan.blade.parser.WrappableParser;
import com.poovarasan.blade.processor.JsonDataProcessor;
import com.poovarasan.blade.toolbox.Styles;
import com.poovarasan.blade.view.BladeView;
import com.shpt.lib.kernel.adapter.CustomViewPagerAdapter;
import com.shpt.lib.kernel.component.CustomViewPager;

/**
 * Created by poovarasanv on 24/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 24/7/17 at 10:57 AM
 */

public class CustomViewPagerParser extends WrappableParser<CustomViewPager> {
    public CustomViewPagerParser(Parser<CustomViewPager> wrappedParser) {
        super(wrappedParser);
    }

    @Override
    public BladeView createView(ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index) {
        return new CustomViewPager(parent.getContext());
    }


    @Override
    protected void prepareHandlers() {
        super.prepareHandlers();

        addHandler(new Attributes.Attribute("layoutData"), new JsonDataProcessor<CustomViewPager>() {
            @Override
            public void handle(String key, JsonElement value, CustomViewPager view) {
                CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(view.getContext(), value.getAsJsonArray());
                view.setAdapter(customViewPagerAdapter);
                view.setOffscreenPageLimit(value.getAsJsonArray().size());
            }
        });
    }
}
