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

package com.poovarasan.blade.builder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.poovarasan.blade.DataContext;
import com.poovarasan.blade.parser.LayoutHandler;
import com.poovarasan.blade.toolbox.BitmapLoader;
import com.poovarasan.blade.toolbox.BladeConstants;
import com.poovarasan.blade.toolbox.IdGenerator;
import com.poovarasan.blade.toolbox.Styles;
import com.poovarasan.blade.toolbox.Utils;
import com.poovarasan.blade.view.BladeView;
import com.poovarasan.blade.view.manager.BladeViewManager;
import com.poovarasan.blade.view.manager.BladeViewManagerImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * A layout builder which can parse json to construct an android view out of it. It uses the
 * registered handlers to convert the json string to a view and then assign attributes.
 */
public class SimpleLayoutBuilder implements LayoutBuilder {

    private static final String TAG = "SimpleLayoutBuilder";

    @Nullable
    protected LayoutBuilderCallback listener;
    private HashMap<String, LayoutHandler> layoutHandlers = new HashMap<>();
    @Nullable
    private BitmapLoader bitmapLoader;

    private boolean isSynchronousRendering = false;

    private IdGenerator idGenerator;

    protected SimpleLayoutBuilder(@NonNull IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public void registerHandler(String type, LayoutHandler handler) {
        handler.prepareAttributeHandlers();
        layoutHandlers.put(type, handler);
    }

    @Override
    public void unregisterHandler(String type) {
        layoutHandlers.remove(type);
    }

    @Override
    @Nullable
    public LayoutHandler getHandler(String type) {
        return layoutHandlers.get(type);
    }

    @Override
    @Nullable
    public BladeView build(ViewGroup parent, JsonObject layout, JsonObject data, int index, Styles styles) {
        String type = Utils.getPropertyAsString(layout, BladeConstants.TYPE);

        if (type == null) {
            throw new IllegalArgumentException("'type' missing in layout: " + layout.toString());
        }

        LayoutHandler handler = layoutHandlers.get(type);
        if (handler == null) {
            return onUnknownViewEncountered(type, parent, layout, data, index, styles);
        }

        /**
         * View creation.
         */
        final BladeView view;

        onBeforeCreateView(handler, parent, layout, data, index, styles);
        view = createView(handler, parent, layout, data, index, styles);
        onAfterCreateView(handler, view, parent, layout, data, index, styles);

        BladeViewManager viewManager = createViewManager(handler, parent, layout, data, index, styles);
        viewManager.setView((View) view);
        view.setViewManager(viewManager);

        /**
         * Parsing each attribute and setting it on the view.
         */
        JsonElement value;
        String attribute;

        for (Map.Entry<String, JsonElement> entry : layout.entrySet()) {
            if (BladeConstants.TYPE.equals(entry.getKey())) {
                continue;
            }

            value = entry.getValue();
            attribute = entry.getKey();

            boolean handled = handleAttribute(handler, view, attribute, value);

            if (!handled) {
                onUnknownAttributeEncountered(attribute, value, view);
            }
        }

        return view;
    }

    protected void onBeforeCreateView(LayoutHandler handler, ViewGroup parent, JsonObject layout, JsonObject data, int index, Styles styles) {
        handler.onBeforeCreateView(parent, layout, data, styles, index);
    }

    protected BladeView createView(LayoutHandler handler, ViewGroup parent, JsonObject layout, JsonObject data, int index, Styles styles) {
        return handler.createView(parent, layout, data, styles, index);
    }

    protected void onAfterCreateView(LayoutHandler handler, BladeView view, ViewGroup parent, JsonObject layout, JsonObject data, int index, Styles styles) {
        //noinspection unchecked
        handler.onAfterCreateView((View) view, parent, layout, data, styles, index);
    }

    protected BladeViewManager createViewManager(LayoutHandler handler, View parent, JsonObject layout, JsonObject data, int index, Styles styles) {
        if (BladeConstants.isLoggingEnabled()) {
            Log.d(TAG, "BladeView created with " + Utils.getLayoutIdentifier(layout));
        }

        BladeViewManagerImpl viewManager = new BladeViewManagerImpl();
        DataContext dataContext = new DataContext();
        dataContext.setData(data);
        dataContext.setIndex(index);

        viewManager.setLayout(layout);
        viewManager.setDataContext(dataContext);
        viewManager.setStyles(styles);
        viewManager.setLayoutBuilder(this);
        viewManager.setLayoutHandler(handler);

        return viewManager;
    }

    public boolean handleAttribute(LayoutHandler handler, BladeView view, String attribute, JsonElement value) {
        if (BladeConstants.isLoggingEnabled()) {
            Log.d(TAG, "Handle '" + attribute + "' : " + value.toString() + " for view with " + Utils.getLayoutIdentifier(view.getViewManager().getLayout()));
        }
        //noinspection unchecked
        return handler.handleAttribute((View) view, attribute, value);
    }

    protected void onUnknownAttributeEncountered(String attribute, JsonElement value, BladeView view) {
        if (listener != null) {
            listener.onUnknownAttribute(attribute, value, view);
        }
    }

    @Nullable
    protected BladeView onUnknownViewEncountered(String type, ViewGroup parent, JsonObject layout, JsonObject data, int index, Styles styles) {
        if (BladeConstants.isLoggingEnabled()) {
            Log.d(TAG, "No LayoutHandler for: " + type);
        }
        if (listener != null) {
            return listener.onUnknownViewType(type, parent, layout, data, index, styles);
        }
        return null;
    }

    @Override
    public int getUniqueViewId(String id) {
        return idGenerator.getUnique(id);
    }

    @Nullable
    @Override
    public IdGenerator getIdGenerator() {
        return idGenerator;
    }

    @Nullable
    @Override
    public LayoutBuilderCallback getListener() {
        return listener;
    }

    @Override
    public void setListener(@Nullable LayoutBuilderCallback listener) {
        this.listener = listener;
    }

    @Override
    public BitmapLoader getNetworkDrawableHelper() {
        return bitmapLoader;
    }

    @Override
    public void setBitmapLoader(@Nullable BitmapLoader bitmapLoader) {
        this.bitmapLoader = bitmapLoader;
    }

    @Override
    public boolean isSynchronousRendering() {
        return isSynchronousRendering;
    }

    @Override
    public void setSynchronousRendering(boolean isSynchronousRendering) {
        this.isSynchronousRendering = isSynchronousRendering;
    }

}
