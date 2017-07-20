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
import com.shpt.lib.kernel.component.JustifiedTextViewComponent;

/**
 * Created by poovarasanv on 5/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 5/7/17 at 10:15 AM
 */

public class JustifiedTextViewParser extends WrappableParser<JustifiedTextViewComponent> {
    public JustifiedTextViewParser(Parser<JustifiedTextViewComponent> wrappedParser) {
        super(wrappedParser);
    }

    @Override
    public BladeView createView(ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index) {
        return new JustifiedTextViewComponent(parent.getContext());
    }

    @Override
    protected void prepareHandlers() {
        super.prepareHandlers();

        addHandler(new Attributes.Attribute("text"), new StringAttributeProcessor<JustifiedTextViewComponent>() {
            @Override
            public void handle(String attributeKey, String attributeValue, JustifiedTextViewComponent view) {
                view.setText(attributeValue);
            }
        });

        addHandler(new Attributes.Attribute("textColor"), new ColorResourceProcessor<JustifiedTextViewComponent>() {
            @Override
            public void setColor(JustifiedTextViewComponent view, int color) {
                view.setTextColor(color);
            }

            @Override
            public void setColor(JustifiedTextViewComponent view, ColorStateList colors) {
                view.setTextColor(colors.getDefaultColor());
            }
        });

        addHandler(new Attributes.Attribute("textSize"), new DimensionAttributeProcessor<JustifiedTextViewComponent>() {
            @Override
            public void setDimension(float dimension, JustifiedTextViewComponent view, String key, JsonElement value) {
                view.setTextSize(dimension);
            }
        });
    }
}
