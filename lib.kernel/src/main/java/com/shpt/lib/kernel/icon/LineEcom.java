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

public class LineEcom implements ITypeface {
    private static final String   TTF_FILE = "linea-ecom.ttf";
    private static       Typeface typeface = null;
    private static HashMap<String, Character> mChars;

    @Override
    public IIcon getIcon(String key) {
        return LineEcom.Icon.valueOf(key);
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
        return "lec";
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
        lec_bag('a'),
        lec_bag_check('b'),
        lec_bag_cloud('c'),
        lec_bag_download('d'),
        lec_bag_minus('e'),
        lec_bag_plus('f'),
        lec_bag_refresh('g'),
        lec_bag_remove('h'),
        lec_bag_search('i'),
        lec_bag_upload('j'),
        lec_banknote('k'),
        lec_banknotes('l'),
        lec_basket('m'),
        lec_basket_check('n'),
        lec_basket_cloud('o'),
        lec_basket_download('p'),
        lec_basket_minus('q'),
        lec_basket_plus('r'),
        lec_basket_refresh('s'),
        lec_basket_remove('t'),
        lec_basket_search('u'),
        lec_basket_upload('v'),
        lec_bath('w'),
        lec_cart('x'),
        lec_cart_check('y'),
        lec_cart_cloud('z'),
        lec_cart_content('A'),
        lec_cart_download('B'),
        lec_cart_minus('C'),
        lec_cart_plus('D'),
        lec_cart_refresh('E'),
        lec_cart_remove('F'),
        lec_cart_search('G'),
        lec_cart_upload('H'),
        lec_cent('I'),
        lec_colon('J'),
        lec_creditcard('K'),
        lec_diamond('L'),
        lec_dollar('M'),
        lec_euro('N'),
        lec_franc('O'),
        lec_gift('P'),
        lec_graph1('Q'),
        lec_graph2('R'),
        lec_graph3('S'),
        lec_graph_decrease('T'),
        lec_graph_increase('U'),
        lec_guarani('V'),
        lec_kips('W'),
        lec_lira('X'),
        lec_megaphone('Y'),
        lec_money('Z'),
        lec_naira('0'),
        lec_pesos('1'),
        lec_pound('2'),
        lec_receipt('3'),
        lec_receipt_bath('4'),
        lec_receipt_cent('5'),
        lec_receipt_dollar('6'),
        lec_receipt_euro('7'),
        lec_receipt_franc('8'),
        lec_receipt_guarani('9'),
        lec_receipt_kips('!'),
        lec_receipt_lira('\''),
        lec_receipt_naira('#'),
        lec_receipt_pesos('$'),
        lec_receipt_pound('%'),
        lec_receipt_rublo('&'),
        lec_receipt_rupee('\''),
        lec_receipt_tugrik('('),
        lec_receipt_yen('*'),
        lec_receipt_yen2('+'),
        lec_recept_colon(','),
        lec_rublo('-'),
        lec_rupee('.'),
        lec_safe('/'),
        lec_sale(':'),
        lec_sales(';'),
        lec_ticket('<'),
        lec_tugriks('='),
        lec_wallet('>'),
        lec_won('?'),
        lec_yen('@'),
        lec_yen2('[');


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
                typeface = new LineEcom();
            }
            return typeface;
        }
    }
}
