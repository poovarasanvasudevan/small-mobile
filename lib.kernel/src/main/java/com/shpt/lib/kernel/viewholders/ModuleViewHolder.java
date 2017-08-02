package com.shpt.lib.kernel.viewholders;

import android.view.View;
import android.widget.TextView;

import com.burakeregar.easiestgenericrecycleradapter.base.GenericViewHolder;
import com.shpt.lib.kernel.R;
import com.shpt.lib.kernel.models.ModuleDetail;

/**
 * Created by poovarasanv on 31/7/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 31/7/17 at 4:22 PM
 */

public class ModuleViewHolder extends GenericViewHolder<ModuleDetail> {

    private TextView moduleName;
    private TextView moduleVersion;

    public ModuleViewHolder(View itemView) {
        super(itemView);

        moduleName = itemView.findViewById(R.id.moduleName);
        moduleVersion = itemView.findViewById(R.id.moduleVersion);
    }

    @Override
    public void bindData(ModuleDetail moduleDetail) {
        moduleName.setText(moduleDetail.getModuleName());
        moduleVersion.setText("Version : " + String.valueOf(moduleDetail.getVersion()));
    }
}
