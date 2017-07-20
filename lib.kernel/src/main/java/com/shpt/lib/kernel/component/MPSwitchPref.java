package com.shpt.lib.kernel.component;

import android.content.Context;
import android.util.AttributeSet;

import com.poovarasan.blade.view.BladeView;
import com.poovarasan.blade.view.manager.BladeViewManager;
import com.poovarasan.mpreferences.MaterialSwitchPreference;

/**
 * Created by poovarasanv on 14/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 14/7/17 at 2:40 PM
 */

public class MPSwitchPref extends MaterialSwitchPreference implements BladeView {

    public MPSwitchPref(Context context) {
        super(context);
    }

    public MPSwitchPref(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MPSwitchPref(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MPSwitchPref(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
