package com.shpt.lib.kernel.component;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.poovarasan.blade.view.BladeView;
import com.poovarasan.blade.view.manager.BladeViewManager;
import com.shpt.lib.kernel.R;

/**
 * Created by poovarasanv on 10/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 10/7/17 at 8:27 AM
 */

public class CustomEditText extends AppCompatEditText implements BladeView {
    public CustomEditText(Context context) {
        super(context);

        setBackground(ContextCompat.getDrawable(context, R.drawable.et));
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
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
