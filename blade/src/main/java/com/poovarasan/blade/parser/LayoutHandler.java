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

package com.poovarasan.blade.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import android.view.View;
import android.view.ViewGroup;

import com.poovarasan.blade.processor.AttributeProcessor;
import com.poovarasan.blade.toolbox.Styles;
import com.poovarasan.blade.view.BladeView;

/**
 * @author Poovarasan Vasudevan
 */
public interface LayoutHandler<V extends View> {

    void onBeforeCreateView(ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index);

    BladeView createView(ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index);

    void onAfterCreateView(V view, ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index);

    void prepareAttributeHandlers();

    void addHandler(Attributes.Attribute key, AttributeProcessor<V> handler);

    boolean handleAttribute(V view, String attribute, JsonElement value);

    boolean handleChildren(BladeView view);

    boolean addView(BladeView parent, BladeView view);
}
