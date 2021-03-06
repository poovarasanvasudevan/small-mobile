package com.poovarasan.mpreferences;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by yarolegovich on 01.05.2016.
 */
public class MaterialSwitchPreference extends AbsMaterialCheckablePreference {

    public MaterialSwitchPreference(Context context) {
        super(context);
    }
    public MaterialSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MaterialSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected int getLayout() {
        return R.layout.view_switch_preference;
    }
}
