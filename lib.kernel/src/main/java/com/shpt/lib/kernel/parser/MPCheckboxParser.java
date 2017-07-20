package com.shpt.lib.kernel.parser;

import com.google.gson.JsonObject;

import android.content.res.ColorStateList;
import android.view.ViewGroup;

import com.mikepenz.iconics.IconicsDrawable;
import com.poovarasan.blade.parser.Attributes;
import com.poovarasan.blade.parser.Parser;
import com.poovarasan.blade.parser.WrappableParser;
import com.poovarasan.blade.processor.ColorResourceProcessor;
import com.poovarasan.blade.processor.StringAttributeProcessor;
import com.poovarasan.blade.toolbox.Styles;
import com.poovarasan.blade.view.BladeView;
import com.poovarasan.mpreferences.io.MaterialPreferences;
import com.shpt.lib.kernel.component.MPCheckboxPref;

/**
 * Created by poovarasanv on 14/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 14/7/17 at 1:51 PM
 */

public class MPCheckboxParser extends WrappableParser<MPCheckboxPref> {
    public MPCheckboxParser(Parser<MPCheckboxPref> wrappedParser) {
        super(wrappedParser);
    }

    @Override
    public BladeView createView(ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index) {
        return new MPCheckboxPref(parent.getContext(),null);
    }

    @Override
    protected void prepareHandlers() {
        super.prepareHandlers();


        addHandler(new Attributes.Attribute("title"), new StringAttributeProcessor<MPCheckboxPref>() {
            @Override
            public void handle(String attributeKey, String attributeValue, MPCheckboxPref view) {
                view.setTitle(attributeValue);
            }
        });

        addHandler(new Attributes.Attribute("icon"), new StringAttributeProcessor<MPCheckboxPref>() {
            @Override
            public void handle(String attributeKey, String attributeValue, MPCheckboxPref view) {
                view.setIcon(new IconicsDrawable(view.getContext(), attributeValue).actionBar());
            }
        });

        addHandler(new Attributes.Attribute("iconColor"), new ColorResourceProcessor<MPCheckboxPref>() {
            @Override
            public void setColor(MPCheckboxPref view, int color) {
                view.setIconColor(color);
            }

            @Override
            public void setColor(MPCheckboxPref view, ColorStateList colors) {
                view.setIconColor(colors.getDefaultColor());
            }
        });

        addHandler(new Attributes.Attribute("summary"), new StringAttributeProcessor<MPCheckboxPref>() {
            @Override
            public void handle(String attributeKey, String attributeValue, MPCheckboxPref view) {
                view.setSummary(attributeValue);
            }
        });

        addHandler(new Attributes.Attribute("key"), new StringAttributeProcessor<MPCheckboxPref>() {
            @Override
            public void handle(String attributeKey, String attributeValue, MPCheckboxPref view) {
                String[] key = attributeValue.split("|");

                view.setKey(key[0]);

                if (key[1] != null) {
                    if (MaterialPreferences.getStorageModule(view.getContext()).getBoolean(key[0], Boolean.parseBoolean(key[1]))== Boolean.parseBoolean(key[1])) {
                        view.setValue(Boolean.parseBoolean(key[1]));
                    } else {
                        view.setValue(MaterialPreferences.getStorageModule(view.getContext()).getBoolean(key[0], Boolean.parseBoolean(key[1])));
                    }
                }
            }
        });


    }
}
