package com.shpt.lib.kernel.parser;

import com.google.gson.JsonObject;

import android.content.res.ColorStateList;
import android.view.ViewGroup;

import com.poovarasan.blade.parser.Attributes;
import com.poovarasan.blade.parser.Parser;
import com.poovarasan.blade.parser.WrappableParser;
import com.poovarasan.blade.processor.ColorResourceProcessor;
import com.poovarasan.blade.processor.StringAttributeProcessor;
import com.poovarasan.blade.toolbox.Styles;
import com.poovarasan.blade.view.BladeView;
import com.shpt.lib.kernel.component.CustomEditText;

/**
 * Created by poovarasanv on 10/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 10/7/17 at 8:28 AM
 */

public class CustomEditTextParser extends WrappableParser<CustomEditText> {
    public CustomEditTextParser(Parser<CustomEditText> wrappedParser) {
        super(wrappedParser);
    }

    @Override
    public BladeView createView(ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index) {
        return new CustomEditText(parent.getContext());
    }

    @Override
    protected void prepareHandlers() {
        super.prepareHandlers();

        addHandler(new Attributes.Attribute("hint"), new StringAttributeProcessor<CustomEditText>() {
            @Override
            public void handle(String attributeKey, String attributeValue, CustomEditText view) {
                view.setHint(attributeValue);
            }
        });


        addHandler(new Attributes.Attribute("text"), new StringAttributeProcessor<CustomEditText>() {
            @Override
            public void handle(String attributeKey, String attributeValue, CustomEditText view) {
                view.setText(attributeValue);
            }
        });

        addHandler(new Attributes.Attribute("textColor"), new ColorResourceProcessor<CustomEditText>() {
            @Override
            public void setColor(CustomEditText view, int color) {
                view.setTextColor(color);
            }

            @Override
            public void setColor(CustomEditText view, ColorStateList colors) {
                view.setTextColor(colors);
            }
        });

        addHandler(new Attributes.Attribute("singleLine"), new StringAttributeProcessor<CustomEditText>() {
            @Override
            public void handle(String attributeKey, String attributeValue, CustomEditText view) {
                if(attributeValue.equalsIgnoreCase("true")) {
                    view.setSingleLine(true);
                }
            }
        });
    }
}
