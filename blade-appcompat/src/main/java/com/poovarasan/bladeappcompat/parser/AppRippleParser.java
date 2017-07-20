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
import com.poovarasan.bladeappcompat.widget.AppRipple;

/**
 * Created by poovarasanv on 4/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 4/7/17 at 1:13 PM
 */

public class AppRippleParser extends WrappableParser<AppRipple> {
    public AppRippleParser(Parser<AppRipple> wrappedParser) {
        super(wrappedParser);
    }

    @Override
    public BladeView createView(ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index) {
        return new AppRipple(parent.getContext());
    }

    @Override
    protected void prepareHandlers() {
        super.prepareHandlers();

        addHandler(new Attributes.Attribute("rippleColor"), new ColorResourceProcessor<AppRipple>() {
            @Override
            public void setColor(AppRipple view, int color) {
                view.setRippleColor(color);
            }

            @Override
            public void setColor(AppRipple view, ColorStateList colors) {
                view.setRippleColor(colors.getDefaultColor());
            }
        });

        addHandler(new Attributes.Attribute("rippleBackground"), new ColorResourceProcessor<AppRipple>() {
            @Override
            public void setColor(AppRipple view, int color) {
                view.setRippleBackground(color);
            }

            @Override
            public void setColor(AppRipple view, ColorStateList colors) {
                view.setRippleBackground(colors.getDefaultColor());
            }
        });

        addHandler(new Attributes.Attribute("rippleOverlay"), new StringAttributeProcessor<AppRipple>() {
            @Override
            public void handle(String attributeKey, String attributeValue, AppRipple view) {
                if (attributeValue.equalsIgnoreCase("true")) {
                    view.setRippleOverlay(true);
                } else {
                    view.setRippleOverlay(false);
                }
            }
        });
    }
}
