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

package com.poovarasan.blade.view.manager;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import android.support.annotation.Nullable;
import android.view.View;

import com.poovarasan.blade.DataContext;
import com.poovarasan.blade.binding.Binding;
import com.poovarasan.blade.builder.LayoutBuilder;
import com.poovarasan.blade.parser.LayoutHandler;
import com.poovarasan.blade.toolbox.Styles;

/**
 * BladeViewManager
 *
 * @author Poovarasan Vasudevan
 */
public interface BladeViewManager {

    /**
     * Update the {@link View} with new data.
     *
     * @param data New data for the view
     */
    void update(@Nullable JsonObject data);

    /**
     * Set the {@link View} which will be managed.
     *
     * @param view The view to manage.
     */
    void setView(View view);

    LayoutBuilder getLayoutBuilder();

    void setLayoutBuilder(LayoutBuilder layoutBuilder);

    LayoutHandler getLayoutHandler();

    void setLayoutHandler(LayoutHandler layoutHandler);

    /**
     * Returns the layout used to build this {@link View}.
     *
     * @return Returns the layout used to build this {@link View}
     */
    JsonObject getLayout();

    /**
     * Sets the layout used to build this {@link View}.
     *
     * @param layout The layout used to build this {@link View}
     */
    void setLayout(JsonObject layout);

    /**
     * Returns the current {@link Styles} set in this {@link View}.
     *
     * @return Returns the {@link Styles}.
     */
    @Nullable
    Styles getStyles();

    /**
     * Sets the {@link Styles} to be applied to this {@link View}
     */
    void setStyles(@Nullable Styles styles);

    int getUniqueViewId(String id);

    JsonElement get(String dataPath, int index);

    void set(String dataPath, JsonElement newValue);

    void set(String dataPath, String newValue);

    void set(String dataPath, Number newValue);

    void set(String dataPath, boolean newValue);

    @Nullable
    JsonObject getChildLayout();

    void setChildLayout(@Nullable JsonObject childLayout);

    DataContext getDataContext();

    void setDataContext(DataContext dataContext);

    @Nullable
    String getDataPathForChildren();

    void setDataPathForChildren(@Nullable String dataPathForChildren);

    boolean isViewUpdating();

    void addBinding(Binding binding);

    /**
     * Free all resources held by the view manager
     */
    void destroy();

    void setOnUpdateDataListener(@Nullable OnUpdateDataListener listener);

    void removeOnUpdateDataListener();

    @Nullable
    OnUpdateDataListener getOnUpdateDataListeners();

    interface OnUpdateDataListener {

        JsonObject onBeforeUpdateData(@Nullable JsonObject data);

        JsonObject onAfterDataContext(@Nullable JsonObject data);

        void onUpdateDataComplete(@Nullable JsonObject data);
    }
}
