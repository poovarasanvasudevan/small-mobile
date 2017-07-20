package com.shpt.lib.kernel.component;

import android.content.Context;

import com.poovarasan.blade.view.BladeView;
import com.poovarasan.blade.view.manager.BladeViewManager;
import com.shpt.lib.kernel.widget.JustifiedTextView;

/**
 * Created by poovarasanv on 5/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 5/7/17 at 10:13 AM
 */

public class JustifiedTextViewComponent extends JustifiedTextView implements BladeView {
    BladeViewManager bladeViewManager;

    public JustifiedTextViewComponent(Context context) {
        super(context);
    }

    public JustifiedTextViewComponent(Context context, String text) {
        super(context, text);
    }

    @Override
    public BladeViewManager getViewManager() {
        return bladeViewManager;
    }

    @Override
    public void setViewManager(BladeViewManager bladeViewManager) {
        this.bladeViewManager = bladeViewManager;
    }
}
