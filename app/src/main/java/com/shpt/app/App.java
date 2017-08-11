package com.shpt.app;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.cache.ResponseCacheMiddleware;
import com.koushikdutta.ion.Ion;
import com.poovarasan.mpreferences.io.MaterialPreferences;
import com.poovarasan.mpreferences.io.SharedPrefsStorageFactory;
import com.shpt.R;

import net.wequick.small.Small;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by poovarasanv on 21/6/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 21/6/17 at 9:06 AM
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        Configuration.setDefaults(new Configuration.Defaults() {

            private final JsonProvider jsonProvider = new GsonJsonProvider();
            private final MappingProvider mappingProvider = new GsonMappingProvider();

            @Override
            public JsonProvider jsonProvider() {
                return jsonProvider;
            }

            @Override
            public MappingProvider mappingProvider() {
                return mappingProvider;
            }

            @Override
            public Set<Option> options() {
                return EnumSet.noneOf(Option.class);
            }
        });

        Utils.init(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/releway.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );



        Ion.getDefault(this).configure().setLogging("NETWORK_LOGS", Log.DEBUG);

        try {
            ResponseCacheMiddleware
                    .addCache(AsyncHttpClient.getDefaultInstance(),
                    getFileStreamPath("asynccache"),
                    1024 * 1024 * 10);
        } catch (IOException e) {
            e.printStackTrace();
        }



        MaterialPreferences.instance().setStorageModule(new SharedPrefsStorageFactory("shpt_mp_pref"));

        Small.preSetUp(this);
        Small.setLoadFromAssets(true);
    }
}
