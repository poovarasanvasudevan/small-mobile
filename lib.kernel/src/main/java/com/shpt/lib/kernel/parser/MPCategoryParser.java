package com.shpt.lib.kernel.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import android.content.res.ColorStateList;
import android.view.ViewGroup;

import com.poovarasan.blade.parser.Attributes;
import com.poovarasan.blade.parser.Parser;
import com.poovarasan.blade.parser.WrappableParser;
import com.poovarasan.blade.processor.ColorResourceProcessor;
import com.poovarasan.blade.processor.DimensionAttributeProcessor;
import com.poovarasan.blade.processor.StringAttributeProcessor;
import com.poovarasan.blade.toolbox.Styles;
import com.poovarasan.blade.view.BladeView;
import com.shpt.lib.kernel.component.MPCategory;

/**
 * Created by poovarasanv on 14/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 14/7/17 at 1:49 PM
 */

public class MPCategoryParser extends WrappableParser<MPCategory> {
    public MPCategoryParser(Parser<MPCategory> wrappedParser) {
        super(wrappedParser);
    }

    @Override
    public BladeView createView(ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index) {
        return new MPCategory(parent.getContext());
    }

    @Override
    protected void prepareHandlers() {
        super.prepareHandlers();


        addHandler(new Attributes.Attribute("title"), new StringAttributeProcessor<MPCategory>() {
            @Override
            public void handle(String attributeKey, String attributeValue, MPCategory view) {
                view.setTitle(attributeValue);
            }
        });

        addHandler(new Attributes.Attribute("titleColor"), new ColorResourceProcessor<MPCategory>() {
            @Override
            public void setColor(MPCategory view, int color) {
                view.setTitleColor(color);
            }

            @Override
            public void setColor(MPCategory view, ColorStateList colors) {
                view.setTitleColor(colors.getDefaultColor());
            }
        });

        addHandler(new Attributes.Attribute("cardElevation"), new DimensionAttributeProcessor<MPCategory>() {
            @Override
            public void setDimension(float dimension, MPCategory view, String key, JsonElement value) {
                view.setElevation(dimension);
            }
        });

        addHandler(new Attributes.Attribute("cardCornerRadius"), new DimensionAttributeProcessor<MPCategory>() {
            @Override
            public void setDimension(float dimension, MPCategory view, String key, JsonElement value) {
                view.setRadius(dimension);
            }
        });


        //CommunityMaterial.Icon.cmd_book
        addHandler(new Attributes.Attribute("cardMaxElevation"), new DimensionAttributeProcessor<MPCategory>() {
            @Override
            public void setDimension(float dimension, MPCategory view, String key, JsonElement value) {
                view.setMaxCardElevation(dimension);
            }
        });
    }
}
