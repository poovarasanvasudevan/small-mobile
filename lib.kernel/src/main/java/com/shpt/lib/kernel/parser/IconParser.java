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
import com.poovarasan.blade.processor.DimensionAttributeProcessor;
import com.poovarasan.blade.processor.EventProcessor;
import com.poovarasan.blade.processor.StringAttributeProcessor;
import com.poovarasan.blade.toolbox.Styles;
import com.poovarasan.blade.view.BladeView;
import com.shpt.lib.kernel.component.Icon;

/**
 * Created by poovarasanv on 10/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 10/7/17 at 9:28 AM
 */

public class IconParser extends WrappableParser<Icon> {
    public IconParser(Parser<Icon> wrappedParser) {
        super(wrappedParser);
    }

    @Override
    public BladeView createView(ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index) {
        return new Icon(parent.getContext());
    }

    @Override
    protected void prepareHandlers() {
        super.prepareHandlers();

        addHandler(new Attributes.Attribute("icon"), new StringAttributeProcessor<Icon>() {
            @Override
            public void handle(String attributeKey, String attributeValue, Icon view) {
                //FontAwesome.Icon.faw_barcode
                //Pixeden7Stroke.Icon.pe7_7s_search
                view.setImageDrawable(new IconicsDrawable(view.getContext(), attributeValue).sizeDp(30));
            }
        });

        addHandler(new Attributes.Attribute("size"), new DimensionAttributeProcessor<Icon>() {
            @Override
            public void setDimension(float dimension, Icon view, String key, JsonElement value) {
                view.getIcon().sizeDp(Math.round(dimension));
            }
        });

        addHandler(new Attributes.Attribute("onClick"), new EventProcessor<Icon>() {
            @Override
            public void setOnEventListener(Icon view, final JsonElement attributeValue) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fireEvent((BladeView) view, EventType.OnClick,attributeValue);
                    }
                });
            }
        });

        addHandler(new Attributes.Attribute("iconColor"), new ColorResourceProcessor<Icon>() {
            @Override
            public void setColor(Icon view, int color) {
                view.setColor(color);
            }

            @Override
            public void setColor(Icon view, ColorStateList colors) {
                view.setColor(colors.getDefaultColor());
            }
        });
    }
}
