package com.poovarasan.bladedesign.widget;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;

import com.poovarasan.blade.view.BladeView;
import com.poovarasan.blade.view.manager.BladeViewManager;

/**
 * Created by poovarasanv on 10/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 10/7/17 at 2:52 PM
 */

public class BladeDrawerLayout extends DrawerLayout implements BladeView {
    public BladeDrawerLayout(Context context) {
        super(context);
        this.setFitsSystemWindows(true);
    }

    public BladeDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BladeDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
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
