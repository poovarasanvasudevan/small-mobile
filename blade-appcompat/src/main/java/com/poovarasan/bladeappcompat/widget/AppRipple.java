package com.poovarasan.bladeappcompat.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.poovarasan.blade.view.BladeView;
import com.poovarasan.blade.view.manager.BladeViewManager;
import com.poovarasan.bladeappcompat.custom.MaterialRippleLayout;

/**
 * Created by poovarasanv on 4/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 4/7/17 at 1:11 PM
 */

public class AppRipple extends MaterialRippleLayout implements BladeView {
    public AppRipple(Context context) {
        super(context);

        super.setRippleColor(Color.GRAY);
        super.setRippleOverlay(true);
    }

    public AppRipple(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AppRipple(Context context, AttributeSet attrs, int defStyle) {
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
