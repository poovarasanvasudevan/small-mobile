package com.shpt.lib.kernel.icon;

import android.content.Context;
import android.graphics.Typeface;

import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.typeface.ITypeface;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by poovarasanv on 11/8/17.
 *
 * @author poovarasanv
 * @project SHPT2
 * @on 11/8/17 at 11:56 AM
 */

public class LineBasic implements ITypeface {
    private static final String   TTF_FILE = "linea-basic.ttf";
    private static       Typeface typeface = null;
    private static HashMap<String, Character> mChars;

    @Override
    public IIcon getIcon(String key) {
        return LineBasic.Icon.valueOf(key);
    }

    @Override
    public HashMap<String, Character> getCharacters() {
        if (mChars == null) {
            HashMap<String, Character> aChars = new HashMap<String, Character>();
            for (SimpleLineIcon.Icon v : SimpleLineIcon.Icon.values()) {
                aChars.put(v.name(), v.character);
            }
            mChars = aChars;
        }
        return mChars;
    }

    @Override
    public String getMappingPrefix() {
        return "lin";
    }

    @Override
    public String getFontName() {
        return "Simple Line Icon";
    }

    @Override
    public String getVersion() {
        return "1.0.0.0";
    }

    @Override
    public int getIconCount() {
        return mChars.size();
    }

    @Override
    public Collection<String> getIcons() {
        Collection<String> icons = new LinkedList<String>();
        for (SimpleLineIcon.Icon value : SimpleLineIcon.Icon.values()) {
            icons.add(value.name());
        }
        return icons;
    }

    @Override
    public String getAuthor() {
        return "Poovarasan";
    }

    @Override
    public String getUrl() {
        return "";
    }

    @Override
    public String getDescription() {
        return "Simple Line Icon";
    }

    @Override
    public String getLicense() {
        return "";
    }

    @Override
    public String getLicenseUrl() {
        return "http://android-iconics.mikepenz.com/";
    }


    @Override
    public Typeface getTypeface(Context context) {
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + TTF_FILE);
            } catch (Exception e) {
                return null;
            }
        }
        return typeface;
    }

    public enum Icon implements IIcon {

        lin_accelerator('a'),
        lin_alarm('b'),
        lin_anchor('c'),
        lin_anticlockwise('d'),
        lin_archive('e'),
        lin_archive_full('f'),
        lin_ban('g'),
        lin_battery_charge('h'),
        lin_battery_empty('i'),
        lin_battery_full('j'),
        lin_battery_half('k'),
        lin_bolt('l'),
        lin_book('m'),
        lin_book_pen('n'),
        lin_book_pencil('o'),
        lin_bookmark('p'),
        lin_calculator('q'),
        lin_calendar('r'),
        lin_cards_diamonds('s'),
        lin_cards_hearts('t'),
        lin_case('u'),
        lin_chronometer('v'),
        lin_clessidre('w'),
        lin_clock('x'),
        lin_clockwise('y'),
        lin_cloud('z'),
        lin_clubs('A'),
        lin_compass('B'),
        lin_cup('C'),
        lin_diamonds('D'),
        lin_display('E'),
        lin_download('F'),
        lin_exclamation('G'),
        lin_eye('H'),
        lin_eye_closed('I'),
        lin_female('J'),
        lin_flag1('K'),
        lin_flag2('L'),
        lin_floppydisk('M'),
        lin_folder('N'),
        lin_folder_multiple('O'),
        lin_gear('P'),
        lin_geolocalize_01('Q'),
        lin_geolocalize_05('R'),
        lin_globe('S'),
        lin_gunsight('T'),
        lin_hammer('U'),
        lin_headset('V'),
        lin_heart('W'),
        lin_heart_broken('X'),
        lin_helm('Y'),
        lin_home('Z'),
        lin_info('0'),
        lin_ipod('1'),
        lin_joypad('2'),
        lin_key('3'),
        lin_keyboard('4'),
        lin_laptop('5'),
        lin_life_buoy('6'),
        lin_lightbulb('7'),
        lin_link('8'),
        lin_lock('9'),
        lin_lock_open('!'),
        lin_magic_mouse('\''),
        lin_magnifier('#'),
        lin_magnifier_minus('$'),
        lin_magnifier_plus('%'),
        lin_mail('&'),
        lin_mail_multiple('\''),
        lin_mail_open('('),
        lin_mail_open_text(')'),
        lin_male('*'),
        lin_map('+'),
        lin_message(','),
        lin_message_multiple('_'),
        lin_message_txt('.'),
        lin_mixer2('/'),
        lin_mouse(':'),
        lin_notebook(';'),
        lin_notebook_pen('<'),
        lin_notebook_pencil('='),
        lin_paperplane('>'),
        lin_pencil_ruler('?'),
        lin_pencil_ruler_pen('@'),
        lin_photo('['),
        lin_picture(']'),
        lin_picture_multiple('^'),
        lin_pin1('_'),
        lin_pin2('`'),
        lin_postcard('('),
        lin_postcard_multiple('|'),
        lin_printer(')'),
        lin_question('~'),
        lin_rss('\\'),
        lin_server('\ue000'),
        lin_server2('\ue001'),
        lin_server_cloud('\ue002'),
        lin_server_download('\ue003'),
        lin_server_upload('\ue004'),
        lin_settings('\ue005'),
        lin_share('\ue006'),
        lin_sheet('\ue007'),
        lin_sheet_multiple('\ue008'),
        lin_sheet_pen('\ue009'),
        lin_sheet_pencil('\ue00a'),
        lin_sheet_txt('\ue00b'),
        lin_signs('\ue00c'),
        lin_smartphone('\ue00d'),
        lin_spades('\ue00e'),
        lin_spread('\ue00f'),
        lin_spread_bookmark('\ue010'),
        lin_spread_text('\ue011'),
        lin_spread_text_bookmark('\ue012'),
        lin_star('\ue013'),
        lin_tablet('\ue014'),
        lin_target('\ue015'),
        lin_todo('\ue016'),
        lin_todo_pen('\ue017'),
        lin_todo_pencil('\ue018'),
        lin_todo_txt('\ue019'),
        lin_todolist_pen('\ue01a'),
        lin_todolist_pencil('\ue01b'),
        lin_trashcan('\ue01c'),
        lin_trashcan_full('\ue01d'),
        lin_trashcan_refresh('\ue01e'),
        lin_trashcan_remove('\ue01f'),
        lin_upload('\ue020'),
        lin_usb('\ue021'),
        lin_video('\ue022'),
        lin_watch('\ue023'),
        lin_webpage('\ue024'),
        lin_webpage_img_txt('\ue025'),
        lin_webpage_multiple('\ue026'),
        lin_webpage_txt('\ue027'),
        lin_world('\ue028');

        char character;

        Icon(char character) {
            this.character = character;
        }

        public String getFormattedName() {
            return "{" + name() + "}";
        }

        public char getCharacter() {
            return character;
        }

        public String getName() {
            return name();
        }

        // remember the typeface so we can use it later
        private static ITypeface typeface;

        public ITypeface getTypeface() {
            if (typeface == null) {
                typeface = new LineBasic();
            }
            return typeface;
        }
    }
}
