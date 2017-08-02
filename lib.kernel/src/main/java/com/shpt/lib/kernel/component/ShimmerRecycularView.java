package com.shpt.lib.kernel.component;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.poovarasan.blade.view.BladeView;
import com.poovarasan.blade.view.manager.BladeViewManager;

/**
 * Created by poovarasanv on 24/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 24/7/17 at 8:51 AM
 */

public class ShimmerRecycularView extends ShimmerRecyclerView implements BladeView {
    public ShimmerRecycularView(Context context) {
        super(context);
    }

    public ShimmerRecycularView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShimmerRecycularView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    BladeViewManager bladeViewManager;

    @Override
    public BladeViewManager getViewManager() {
        return bladeViewManager;
    }

    @Override
    public void setViewManager(BladeViewManager bladeViewManager) {
        this.bladeViewManager = bladeViewManager;
    }
}

