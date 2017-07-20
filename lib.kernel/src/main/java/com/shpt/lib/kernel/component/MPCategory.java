package com.shpt.lib.kernel.component;

import android.content.Context;
import android.util.AttributeSet;

import com.poovarasan.blade.view.BladeView;
import com.poovarasan.blade.view.manager.BladeViewManager;
import com.poovarasan.mpreferences.MaterialPreferenceCategory;

/**
 * Created by poovarasanv on 14/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 14/7/17 at 1:44 PM
 */

public class MPCategory extends MaterialPreferenceCategory implements BladeView {
    public MPCategory(Context context) {
        super(context);
    }

    public MPCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MPCategory(Context context, AttributeSet attrs, int defStyleAttr) {
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
