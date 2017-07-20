package com.poovarasan.bladeappcompat.parser;

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
import com.poovarasan.bladeappcompat.widget.AppButton;

/**
 * Created by poovarasanv on 23/3/17.
 *
 * @author poovarasanv
 * @project SHPT
 * @on 23/3/17 at 9:54 AM
 */

public class AppButtonParser extends WrappableParser<AppButton> {
    public AppButtonParser(Parser<AppButton> wrappedParser) {
        super(wrappedParser);
    }

    @Override
    public BladeView createView(ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index) {
        return new AppButton(parent.getContext());
    }

    @Override
    protected void prepareHandlers() {
        super.prepareHandlers();

        addHandler(new Attributes.Attribute("btnBackgroundColor"), new ColorResourceProcessor<AppButton>() {
            @Override
            public void setColor(AppButton view, int color) {
                view.setBackgroundColor(color);
            }

            @Override
            public void setColor(AppButton view, ColorStateList colors) {
                view.setBackgroundColor(colors.getDefaultColor());
            }
        });

        addHandler(new Attributes.Attribute("text"), new StringAttributeProcessor<AppButton>() {
            @Override
            public void handle(String attributeKey, String attributeValue, AppButton view) {
                view.setText(attributeValue);
            }
        });


        addHandler(new Attributes.Attribute("textColor"), new ColorResourceProcessor<AppButton>() {
            @Override
            public void setColor(AppButton view, int color) {
                view.setTextColor(color);
            }

            @Override
            public void setColor(AppButton view, ColorStateList colors) {
                view.setTextColor(colors);
            }
        });
    }
}
