/*
 * Copyright 2016 Poovarasan Vasudevan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.poovarasan.blade.parser.custom;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

import com.poovarasan.blade.parser.Attributes;
import com.poovarasan.blade.parser.ParseHelper;
import com.poovarasan.blade.parser.Parser;
import com.poovarasan.blade.parser.WrappableParser;
import com.poovarasan.blade.processor.DimensionAttributeProcessor;
import com.poovarasan.blade.processor.DrawableResourceProcessor;
import com.poovarasan.blade.processor.StringAttributeProcessor;
import com.poovarasan.blade.toolbox.Styles;
import com.poovarasan.blade.view.BladeFixedRatingBar;
import com.poovarasan.blade.view.BladeView;
import com.poovarasan.blade.view.custom.FixedRatingBar;

/**
 * Created by Poovarasan Vasudevan on 12/05/14.
 */
public class RatingBarParser<T extends FixedRatingBar> extends WrappableParser<T> {

    public RatingBarParser(Parser<T> wrappedParser) {
        super(wrappedParser);
    }

    @Override
    public BladeView createView(ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index) {
        return new BladeFixedRatingBar(parent.getContext());
    }

    @Override
    protected void prepareHandlers() {
        super.prepareHandlers();
        addHandler(Attributes.RatingBar.NumStars, new StringAttributeProcessor<T>() {
            @Override
            public void handle(String attributeKey, String attributeValue, T view) {
                view.setNumStars(ParseHelper.parseInt(attributeValue));
            }
        });
        addHandler(Attributes.RatingBar.Rating, new StringAttributeProcessor<T>() {
            @Override
            public void handle(String attributeKey, String attributeValue, T view) {
                view.setRating(ParseHelper.parseFloat(attributeValue));
            }
        });
        addHandler(Attributes.RatingBar.IsIndicator, new StringAttributeProcessor<T>() {
            @Override
            public void handle(String attributeKey, String attributeValue, T view) {
                view.setIsIndicator(ParseHelper.parseBoolean(attributeValue));
            }
        });
        addHandler(Attributes.RatingBar.StepSize, new StringAttributeProcessor<T>() {
            @Override
            public void handle(String attributeKey, String attributeValue, T view) {
                view.setStepSize(ParseHelper.parseFloat(attributeValue));
            }
        });
        addHandler(Attributes.RatingBar.MinHeight, new DimensionAttributeProcessor<T>() {
            @Override
            public void setDimension(float dimension, T view, String key, JsonElement value) {
                view.setMinimumHeight((int) dimension);
            }
        });
        addHandler(Attributes.RatingBar.ProgressDrawable, new DrawableResourceProcessor<T>() {
            @Override
            public void setDrawable(T view, Drawable drawable) {
                drawable = view.getTiledDrawable(drawable, false);
                view.setProgressDrawable(drawable);
            }
        });
    }
}
