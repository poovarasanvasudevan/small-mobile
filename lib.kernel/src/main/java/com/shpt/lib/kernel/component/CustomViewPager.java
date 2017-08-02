package com.shpt.lib.kernel.component;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.poovarasan.blade.view.BladeView;
import com.poovarasan.blade.view.manager.BladeViewManager;

/**
 * Created by poovarasanv on 24/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 24/7/17 at 10:56 AM
 */

public class CustomViewPager extends ViewPager implements BladeView {
    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
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
