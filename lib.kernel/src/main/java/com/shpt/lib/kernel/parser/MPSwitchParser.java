package com.shpt.lib.kernel.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import android.content.res.ColorStateList;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.iconics.IconicsDrawable;
import com.poovarasan.blade.EventType;
import com.poovarasan.blade.parser.Attributes;
import com.poovarasan.blade.parser.Parser;
import com.poovarasan.blade.parser.WrappableParser;
import com.poovarasan.blade.processor.ColorResourceProcessor;
import com.poovarasan.blade.processor.EventProcessor;
import com.poovarasan.blade.processor.StringAttributeProcessor;
import com.poovarasan.blade.toolbox.Styles;
import com.poovarasan.blade.view.BladeView;
import com.poovarasan.mpreferences.io.MaterialPreferences;
import com.shpt.lib.kernel.component.MPSwitchPref;

/**
 * Created by poovarasanv on 14/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 14/7/17 at 2:42 PM
 */

public class MPSwitchParser extends WrappableParser<MPSwitchPref> {
    public MPSwitchParser(Parser<MPSwitchPref> wrappedParser) {
        super(wrappedParser);
    }

    @Override
    public BladeView createView(ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index) {
        return new MPSwitchPref(parent.getContext());
    }

    @Override
    protected void prepareHandlers() {
        super.prepareHandlers();


        addHandler(new Attributes.Attribute("title"), new StringAttributeProcessor<MPSwitchPref>() {
            @Override
            public void handle(String attributeKey, String attributeValue, MPSwitchPref view) {
                view.setTitle(attributeValue);
            }
        });

        addHandler(new Attributes.Attribute("icon"), new StringAttributeProcessor<MPSwitchPref>() {
            @Override
            public void handle(String attributeKey, String attributeValue, MPSwitchPref view) {
                view.setIcon(new IconicsDrawable(view.getContext(), attributeValue).actionBar());
            }
        });

        addHandler(new Attributes.Attribute("iconColor"), new ColorResourceProcessor<MPSwitchPref>() {
            @Override
            public void setColor(MPSwitchPref view, int color) {
                view.setIconColor(color);
            }

            @Override
            public void setColor(MPSwitchPref view, ColorStateList colors) {
                view.setIconColor(colors.getDefaultColor());
            }
        });

        addHandler(new Attributes.Attribute("summary"), new StringAttributeProcessor<MPSwitchPref>() {
            @Override
            public void handle(String attributeKey, String attributeValue, MPSwitchPref view) {
                view.setSummary(attributeValue);
            }
        });

        addHandler(new Attributes.Attribute("key"), new StringAttributeProcessor<MPSwitchPref>() {
            @Override
            public void handle(String attributeKey, String attributeValue, MPSwitchPref view) {
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


        addHandler(new Attributes.Attribute("OnClick"), new EventProcessor<MPSwitchPref>() {
            @Override
            public void setOnEventListener(MPSwitchPref view, final JsonElement attributeValue) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fireEvent((BladeView) view, EventType.OnClick, attributeValue);
                    }
                });
            }
        });
    }
}
