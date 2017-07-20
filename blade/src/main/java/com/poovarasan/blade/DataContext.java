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

package com.poovarasan.blade;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import android.support.annotation.Nullable;

import com.poovarasan.blade.toolbox.BladeConstants;
import com.poovarasan.blade.toolbox.Result;
import com.poovarasan.blade.toolbox.Utils;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Poovarasan Vasudevan
 */
public class DataContext {

    private final boolean isClone;
    private JsonObject data;
    @Nullable
    private JsonObject reverseScope;
    @Nullable
    private JsonObject scope;
    private int index;

    public DataContext() {
        this.data = new JsonObject();
        this.scope = new JsonObject();
        this.reverseScope = new JsonObject();
        this.index = -1;
        this.isClone = false;
    }

    public DataContext(DataContext dataContext) {
        this.data = dataContext.getData();
        this.scope = dataContext.getScope();
        this.reverseScope = dataContext.getReverseScope();
        this.index = dataContext.getIndex();
        this.isClone = true;
    }

    public static DataContext updateDataContext(DataContext dataContext, JsonObject data, JsonObject scope, int dataIndex) {
        JsonObject reverseScope = new JsonObject();
        JsonObject newData = new JsonObject();

        dataContext.setIndex(dataIndex);

        if (data == null) {
            data = new JsonObject();
        }

        for (Map.Entry<String, JsonElement> entry : scope.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().getAsString();
            Result result = Utils.readJson(value, data, dataContext.getIndex());
            JsonElement element = result.isSuccess() ? result.element : new JsonObject();
            newData.add(key, element);
            String unAliasedValue = value.replace(BladeConstants.INDEX, String.valueOf(dataContext.getIndex()));
            reverseScope.add(unAliasedValue, new JsonPrimitive(key));
        }

        Utils.addElements(newData, data, false);

        if (dataContext.getData() == null) {
            dataContext.setData(new JsonObject());
        } else {
            dataContext.setData(newData);
        }
        dataContext.setScope(scope);
        dataContext.setReverseScope(reverseScope);
        return dataContext;
    }

    public static String getAliasedDataPath(String dataPath, JsonObject reverseScope, boolean isBindingPath) {
        String[] segments;
        if (isBindingPath) {
            segments = dataPath.split(BladeConstants.DATA_PATH_DELIMITER);
        } else {
            segments = dataPath.split(BladeConstants.DATA_PATH_SIMPLE_DELIMITER);
        }

        if (reverseScope == null) {
            return dataPath;
        }
        String alias = Utils.getPropertyAsString(reverseScope, segments[0]);
        if (alias == null) {
            return dataPath;
        }

        return dataPath.replaceFirst(Pattern.quote(segments[0]), alias);
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }

    @Nullable
    public JsonObject getScope() {
        return scope;
    }

    public void setScope(@Nullable JsonObject scope) {
        this.scope = scope;
    }

    @Nullable
    public JsonObject getReverseScope() {
        return reverseScope;
    }

    public void setReverseScope(@Nullable JsonObject reverseScope) {
        this.reverseScope = reverseScope;
    }

    public boolean isClone() {
        return isClone;
    }

    @Nullable
    public JsonElement get(String dataPath) {
        String aliasedDataPath = getAliasedDataPath(dataPath, reverseScope, true);
        Result result = Utils.readJson(aliasedDataPath, data, index);
        if (result.isSuccess()) {
            return result.element;
        } else if (result.RESULT_CODE == Result.RESULT_JSON_NULL_EXCEPTION) {
            return JsonNull.INSTANCE;
        }
        return null;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public DataContext createChildDataContext(JsonObject scope, int dataIndex) {
        return updateDataContext(new DataContext(), data, scope, dataIndex);
    }

    public void updateDataContext(JsonObject data) {
        updateDataContext(this, data, scope, index);
    }
}
