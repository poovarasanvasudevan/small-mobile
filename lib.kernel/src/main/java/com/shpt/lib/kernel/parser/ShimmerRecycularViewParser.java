package com.shpt.lib.kernel.parser;

import com.google.gson.JsonObject;

import android.view.ViewGroup;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.poovarasan.blade.parser.Attributes;
import com.poovarasan.blade.parser.Parser;
import com.poovarasan.blade.parser.WrappableParser;
import com.poovarasan.blade.processor.StringAttributeProcessor;
import com.poovarasan.blade.toolbox.Styles;
import com.poovarasan.blade.view.BladeView;
import com.shpt.lib.kernel.R;
import com.shpt.lib.kernel.component.ShimmerRecycularView;

/**
 * Created by poovarasanv on 24/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 24/7/17 at 8:54 AM
 */

public class ShimmerRecycularViewParser extends WrappableParser<ShimmerRecycularView> {
    public ShimmerRecycularViewParser(Parser<ShimmerRecycularView> wrappedParser) {
        super(wrappedParser);
    }

    @Override
    public BladeView createView(ViewGroup parent, JsonObject layout, JsonObject data, Styles styles, int index) {
        return new ShimmerRecycularView(parent.getContext());
    }


    @Override
    protected void prepareHandlers() {
        super.prepareHandlers();


        addHandler(new Attributes.Attribute("childCount"), new StringAttributeProcessor<ShimmerRecycularView>() {
            @Override
            public void handle(String attributeKey, String attributeValue, ShimmerRecycularView view) {
                view.setDemoChildCount(Integer.parseInt(attributeValue));
            }
        });


        addHandler(new Attributes.Attribute("layoutManagerType"), new StringAttributeProcessor<ShimmerRecycularView>() {
            @Override
            public void handle(String attributeKey, String attributeValue, ShimmerRecycularView view) {
                if (attributeValue.equalsIgnoreCase("listVertical")) {
                    view.setDemoLayoutManager(ShimmerRecyclerView.LayoutMangerType.LINEAR_VERTICAL);
                }

                if (attributeValue.equalsIgnoreCase("listHorizontal")) {
                    view.setDemoLayoutManager(ShimmerRecyclerView.LayoutMangerType.LINEAR_HORIZONTAL);
                }

                if (attributeKey.equalsIgnoreCase("grid")) {
                    view.setDemoLayoutManager(ShimmerRecyclerView.LayoutMangerType.GRID);
                }
            }
        });


        addHandler(new Attributes.Attribute("layoutID"), new StringAttributeProcessor<ShimmerRecycularView>() {
            @Override
            public void handle(String attributeKey, String attributeValue, ShimmerRecycularView view) {
                if(attributeValue.equalsIgnoreCase("home")) {
                    view.setDemoLayoutReference(R.layout.home_shimmer);
                }
            }
        });

        addHandler(new Attributes.Attribute("gridChildCount"), new StringAttributeProcessor<ShimmerRecycularView>() {
            @Override
            public void handle(String attributeKey, String attributeValue, ShimmerRecycularView view) {
                view.setGridChildCount(Integer.parseInt(attributeValue));
            }
        });
    }
}
