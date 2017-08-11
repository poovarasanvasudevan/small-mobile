package com.shpt.lib.kernel.parser;

import com.google.gson.JsonObject;

import android.view.ViewGroup;

import com.koushikdutta.ion.Ion;
import com.poovarasan.blade.parser.Attributes;
import com.poovarasan.blade.parser.Parser;
import com.poovarasan.blade.parser.WrappableParser;
import com.poovarasan.blade.processor.StringAttributeProcessor;
import com.poovarasan.blade.toolbox.Styles;
import com.poovarasan.blade.view.BladeView;
import com.shpt.lib.kernel.R;
import com.shpt.lib.kernel.component.URLImageView;

/**
 * Created by poovarasanv on 29/6/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 29/6/17 at 3:00 PM
 */

public class URLImageViewParser extends WrappableParser<URLImageView> {
    public URLImageViewParser(Parser<URLImageView> wrappedParser) {
        super(wrappedParser);
    }

    @Override
    public BladeView createView(ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index) {
        return new URLImageView(parent.getContext());
    }

    @Override
    protected void prepareHandlers() {
        super.prepareHandlers();

        addHandler(new Attributes.Attribute("url"), new StringAttributeProcessor<URLImageView>() {
            @Override
            public void handle(String attributeKey, String attributeValue, URLImageView view) {

                Ion.with(view)
                        .placeholder(R.drawable.progress_drawable)
                        .error(R.drawable.progress_drawable)
                        .load(attributeValue);

            }
        });
    }
}
