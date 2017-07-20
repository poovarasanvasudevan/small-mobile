package com.poovarasan.bladedesign.widget;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;

import com.poovarasan.blade.view.BladeView;
import com.poovarasan.blade.view.manager.BladeViewManager;
import com.poovarasan.bladedesign.R;

/**
 * Created by poovarasanv on 10/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 10/7/17 at 2:42 PM
 */

public class BladeNavigationView extends NavigationView implements BladeView {
    public BladeNavigationView(Context context) {
        super(context);

        this.setFitsSystemWindows(true);
        inflateMenu(R.menu.default_menu);
    }

    public BladeNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BladeNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
