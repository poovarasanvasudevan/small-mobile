package com.poovarasan.bladeappcompat;

import com.poovarasan.blade.builder.LayoutBuilder;
import com.poovarasan.blade.module.Module;
import com.poovarasan.blade.parser.ViewParser;
import com.poovarasan.blade.parser.custom.ViewGroupParser;
import com.poovarasan.bladeappcompat.parser.AppButtonParser;
import com.poovarasan.bladeappcompat.parser.AppCheckboxParser;
import com.poovarasan.bladeappcompat.parser.AppProgressBarParser;
import com.poovarasan.bladeappcompat.parser.AppRadioButtonParser;
import com.poovarasan.bladeappcompat.parser.AppRippleParser;
import com.poovarasan.bladeappcompat.parser.AppToolbarParser;

/**
 * Created by poovarasanv on 22/3/17.
 *
 * @author poovarasanv
 * @project SHPT
 * @on 22/3/17 at 6:57 PM
 */

public class AppCompatModule implements Module {

    @Override
    public void register(LayoutBuilder layoutBuilder) {
        ViewParser      viewParser      = new ViewParser();
        ViewGroupParser viewGroupParser = new ViewGroupParser(viewParser);


        layoutBuilder.registerHandler("Toolbar", new AppToolbarParser(viewParser));
        layoutBuilder.registerHandler("Ripple", new AppRippleParser(viewGroupParser));
        layoutBuilder.registerHandler("AppCompatCheckbox", new AppCheckboxParser(viewParser));
        layoutBuilder.registerHandler("AppCompatButton", new AppButtonParser(viewParser));
        layoutBuilder.registerHandler("AppCompatRadioButton", new AppRadioButtonParser(viewParser));
        layoutBuilder.registerHandler("AppCompatProgressBar", new AppProgressBarParser(viewParser));
    }
}
