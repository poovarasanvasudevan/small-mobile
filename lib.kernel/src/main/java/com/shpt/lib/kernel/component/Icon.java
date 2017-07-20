package com.shpt.lib.kernel.component;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.mikepenz.iconics.view.IconicsImageView;
import com.poovarasan.blade.view.BladeView;
import com.poovarasan.blade.view.manager.BladeViewManager;

/**
 * Created by poovarasanv on 10/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 10/7/17 at 9:26 AM
 */

public class Icon extends IconicsImageView implements BladeView {
    public Icon(Context context) {
        super(context);
    }

    public Icon(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Icon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
