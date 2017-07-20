package com.shpt.lib.kernel.component;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.poovarasan.blade.view.BladeView;
import com.poovarasan.blade.view.manager.BladeViewManager;

/**
 * Created by poovarasanv on 29/6/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 29/6/17 at 2:58 PM
 */

public class URLImageView extends android.support.v7.widget.AppCompatImageView implements BladeView {
    public URLImageView(Context context) {
        super(context);
    }

    public URLImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public URLImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
