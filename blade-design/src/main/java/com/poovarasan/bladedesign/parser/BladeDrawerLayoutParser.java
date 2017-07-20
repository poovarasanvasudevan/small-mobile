package com.poovarasan.bladedesign.parser;

import com.google.gson.JsonObject;

import android.view.ViewGroup;

import com.poovarasan.blade.parser.Parser;
import com.poovarasan.blade.parser.WrappableParser;
import com.poovarasan.blade.toolbox.Styles;
import com.poovarasan.blade.view.BladeView;
import com.poovarasan.bladedesign.widget.BladeDrawerLayout;

/**
 * Created by poovarasanv on 10/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 10/7/17 at 2:53 PM
 */

public class BladeDrawerLayoutParser extends WrappableParser<BladeDrawerLayout> {
    public BladeDrawerLayoutParser(Parser<BladeDrawerLayout> wrappedParser) {
        super(wrappedParser);
    }

    @Override
    public BladeView createView(ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index) {
        return new BladeDrawerLayout(parent.getContext());
    }

    @Override
    protected void prepareHandlers() {
        super.prepareHandlers();

    }
}
