package com.poovarasan.bladecardview.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import android.content.res.ColorStateList;
import android.os.Build;
import android.view.ViewGroup;

import com.poovarasan.blade.parser.Attributes;
import com.poovarasan.blade.parser.Parser;
import com.poovarasan.blade.parser.WrappableParser;
import com.poovarasan.blade.processor.ColorResourceProcessor;
import com.poovarasan.blade.processor.DimensionAttributeProcessor;
import com.poovarasan.blade.toolbox.Styles;
import com.poovarasan.blade.view.BladeView;
import com.poovarasan.bladecardview.widget.AppCardView;

/**
 * Created by poovarasanv on 23/3/17.
 *
 * @author poovarasanv
 * @project SHPT
 * @on 23/3/17 at 11:30 AM
 */

public class AppCardViewParser extends WrappableParser<AppCardView> {
    public AppCardViewParser(Parser<AppCardView> wrappedParser) {
        super(wrappedParser);
    }

    @Override
    public BladeView createView(ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index) {
        return new AppCardView(parent.getContext());
    }

    @Override
    protected void prepareHandlers() {
        super.prepareHandlers();

        addHandler(new Attributes.Attribute("cardBackgroundColor"), new ColorResourceProcessor<AppCardView>() {
            @Override
            public void setColor(AppCardView view, int color) {
                view.setCardBackgroundColor(color);
            }

            @Override
            public void setColor(AppCardView view, ColorStateList colors) {
                view.setCardBackgroundColor(colors);
            }
        });

        addHandler(new Attributes.Attribute("cardElevation"), new DimensionAttributeProcessor<AppCardView>() {
            @Override
            public void setDimension(float dimension, AppCardView view, String key, JsonElement value) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.setElevation(dimension);
                }
            }
        });


        addHandler(new Attributes.Attribute("cardCornerRadius"), new DimensionAttributeProcessor<AppCardView>() {
            @Override
            public void setDimension(float dimension, AppCardView view, String key, JsonElement value) {
                view.setRadius(dimension);
            }
        });

        addHandler(new Attributes.Attribute("contentPadding"), new DimensionAttributeProcessor<AppCardView>() {
            @Override
            public void setDimension(float dimension, AppCardView view, String key, JsonElement value) {
                view.setContentPadding((int) dimension, (int) dimension, (int) dimension, (int) dimension);
            }
        });

    }
}
