package com.shpt.lib.kernel.module;

import com.poovarasan.blade.builder.LayoutBuilder;
import com.poovarasan.blade.module.Module;
import com.poovarasan.blade.parser.ViewParser;
import com.poovarasan.blade.parser.custom.ViewGroupParser;
import com.shpt.lib.kernel.parser.CustomEditTextParser;
import com.shpt.lib.kernel.parser.CustomViewPagerParser;
import com.shpt.lib.kernel.parser.IconParser;
import com.shpt.lib.kernel.parser.JustifiedTextViewParser;
import com.shpt.lib.kernel.parser.MPCategoryParser;
import com.shpt.lib.kernel.parser.MPCheckboxParser;
import com.shpt.lib.kernel.parser.MPSwitchParser;
import com.shpt.lib.kernel.parser.ShimmerRecycularViewParser;
import com.shpt.lib.kernel.parser.URLImageViewParser;

/**
 * Created by poovarasanv on 29/6/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 29/6/17 at 3:02 PM
 */

public class CustomModule implements Module {
    @Override
    public void register(LayoutBuilder layoutBuilder) {
        ViewParser      viewParser      = new ViewParser();
        ViewGroupParser viewGroupParser = new ViewGroupParser(viewParser);

        layoutBuilder.registerHandler("URLImageView", new URLImageViewParser(viewGroupParser));
        layoutBuilder.registerHandler("JustifiedTextView", new JustifiedTextViewParser(viewParser));
        layoutBuilder.registerHandler("Icon", new IconParser(viewParser));
        layoutBuilder.registerHandler("CustomEditText", new CustomEditTextParser(viewGroupParser));
        layoutBuilder.registerHandler("MaterialPrefCategory", new MPCategoryParser(viewGroupParser));
        layoutBuilder.registerHandler("MaterialCheckbox", new MPCheckboxParser(viewGroupParser));
        layoutBuilder.registerHandler("MaterialSwitch", new MPSwitchParser(viewGroupParser));
        layoutBuilder.registerHandler("ShimmerRecycularView",new ShimmerRecycularViewParser(viewGroupParser));
        layoutBuilder.registerHandler("CViewPager",new CustomViewPagerParser(viewGroupParser));
        //CommunityMaterial.Icon.cmd_library_books
    }
}
