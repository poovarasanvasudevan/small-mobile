/*
 * Copyright 2014 Mike Penz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shpt.lib.kernel.icon;

import android.content.Context;
import android.graphics.Typeface;

import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.typeface.ITypeface;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class SimpleLineIcon implements ITypeface {
    private static final String TTF_FILE = "simple-line-icon-font-v1.0.0.0.ttf";
    private static Typeface typeface = null;
    private static HashMap<String, Character> mChars;

    @Override
    public IIcon getIcon(String key) {
        return Icon.valueOf(key);
    }

    @Override
    public HashMap<String, Character> getCharacters() {
        if (mChars == null) {
            HashMap<String, Character> aChars = new HashMap<String, Character>();
            for (Icon v : Icon.values()) {
                aChars.put(v.name(), v.character);
            }
            mChars = aChars;
        }
        return mChars;
    }

    @Override
    public String getMappingPrefix() {
        return "sli";
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
        for (Icon value : Icon.values()) {
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
        sli_user('\ue005'),
		sli_people('\ue001'),
		sli_user_female('\ue000'),
		sli_user_follow('\ue002'),
		sli_user_following('\ue003'),
		sli_user_unfollow('\ue004'),
		sli_login('\ue066'),
		sli_logout('\ue065'),
		sli_emotsmile('\ue021'),
		sli_phone('\ue600'),
		sli_call_end('\ue048'),
		sli_call_in('\ue047'),
		sli_call_out('\ue046'),
		sli_map('\ue033'),
		sli_location_pin('\ue096'),
		sli_direction('\ue042'),
		sli_directions('\ue041'),
		sli_compass('\ue045'),
		sli_layers('\ue034'),
		sli_menu('\ue601'),
		sli_list('\ue067'),
		sli_options_vertical('\ue602'),
		sli_options('\ue603'),
		sli_arrow_down('\ue604'),
		sli_arrow_left('\ue605'),
		sli_arrow_right('\ue606'),
		sli_arrow_up('\ue607'),
		sli_arrow_up_circle('\ue078'),
		sli_arrow_left_circle('\ue07a'),
		sli_arrow_right_circle('\ue079'),
		sli_arrow_down_circle('\ue07b'),
		sli_check('\ue080'),
		sli_clock('\ue081'),
		sli_plus('\ue095'),
		sli_minus('\ue615'),
		sli_close('\ue082'),
		sli_event('\ue619'),
		sli_exclamation('\ue617'),
		sli_organization('\ue616'),
		sli_trophy('\ue006'),
		sli_screen_smartphone('\ue010'),
		sli_screen_desktop('\ue011'),
		sli_plane('\ue012'),
		sli_notebook('\ue013'),
		sli_mustache('\ue014'),
		sli_mouse('\ue015'),
		sli_magnet('\ue016'),
		sli_energy('\ue020'),
		sli_disc('\ue022'),
		sli_cursor('\ue06e'),
		sli_cursor_move('\ue023'),
		sli_crop('\ue024'),
		sli_chemistry('\ue026'),
		sli_speedometer('\ue007'),
		sli_shield('\ue00e'),
		sli_screen_tablet('\ue00f'),
		sli_magic_wand('\ue017'),
		sli_hourglass('\ue018'),
		sli_graduation('\ue019'),
		sli_ghost('\ue01a'),
		sli_game_controller('\ue01b'),
		sli_fire('\ue01c'),
		sli_eyeglass('\ue01d'),
		sli_envelope_open('\ue01e'),
		sli_envelope_letter('\ue01f'),
		sli_bell('\ue027'),
		sli_badge('\ue028'),
		sli_anchor('\ue029'),
		sli_wallet('\ue02a'),
		sli_vector('\ue02b'),
		sli_speech('\ue02c'),
		sli_puzzle('\ue02d'),
		sli_printer('\ue02e'),
		sli_present('\ue02f'),
		sli_playlist('\ue030'),
		sli_pin('\ue031'),
		sli_picture('\ue032'),
		sli_handbag('\ue035'),
		sli_globe_alt('\ue036'),
		sli_globe('\ue037'),
		sli_folder_alt('\ue039'),
		sli_folder('\ue089'),
		sli_film('\ue03a'),
		sli_feed('\ue03b'),
		sli_drop('\ue03e'),
		sli_drawer('\ue03f'),
		sli_docs('\ue040'),
		sli_doc('\ue085'),
		sli_diamond('\ue043'),
		sli_cup('\ue044'),
		sli_calculator('\ue049'),
		sli_bubbles('\ue04a'),
		sli_briefcase('\ue04b'),
		sli_book_open('\ue04c'),
		sli_basket_loaded('\ue04d'),
		sli_basket('\ue04e'),
		sli_bag('\ue04f'),
		sli_action_undo('\ue050'),
		sli_action_redo('\ue051'),
		sli_wrench('\ue052'),
		sli_umbrella('\ue053'),
		sli_trash('\ue054'),
		sli_tag('\ue055'),
		sli_support('\ue056'),
		sli_frame('\ue038'),
		sli_size_fullscreen('\ue057'),
		sli_size_actual('\ue058'),
		sli_shuffle('\ue059'),
		sli_share_alt('\ue05a'),
		sli_share('\ue05b'),
		sli_rocket('\ue05c'),
		sli_question('\ue05d'),
		sli_pie_chart('\ue05e'),
		sli_pencil('\ue05f'),
		sli_note('\ue060'),
		sli_loop('\ue064'),
		sli_home('\ue069'),
		sli_grid('\ue06a'),
		sli_graph('\ue06b'),
		sli_microphone('\ue063'),
		sli_music_tone_alt('\ue061'),
		sli_music_tone('\ue062'),
		sli_earphones_alt('\ue03c'),
		sli_earphones('\ue03d'),
		sli_equalizer('\ue06c'),
		sli_like('\ue068'),
		sli_dislike('\ue06d'),
		sli_control_start('\ue06f'),
		sli_control_rewind('\ue070'),
		sli_control_play('\ue071'),
		sli_control_pause('\ue072'),
		sli_control_forward('\ue073'),
		sli_control_end('\ue074'),
		sli_volume_1('\ue09f'),
		sli_volume_2('\ue0a0'),
		sli_volume_off('\ue0a1'),
		sli_calendar('\ue075'),
		sli_bulb('\ue076'),
		sli_chart('\ue077'),
		sli_ban('\ue07c'),
		sli_bubble('\ue07d'),
		sli_camrecorder('\ue07e'),
		sli_camera('\ue07f'),
		sli_cloud_download('\ue083'),
		sli_cloud_upload('\ue084'),
		sli_envelope('\ue086'),
		sli_eye('\ue087'),
		sli_flag('\ue088'),
		sli_heart('\ue08a'),
		sli_info('\ue08b'),
		sli_key('\ue08c'),
		sli_link('\ue08d'),
		sli_lock('\ue08e'),
		sli_lock_open('\ue08f'),
		sli_magnifier('\ue090'),
		sli_magnifier_add('\ue091'),
		sli_magnifier_remove('\ue092'),
		sli_paper_clip('\ue093'),
		sli_paper_plane('\ue094'),
		sli_power('\ue097'),
		sli_refresh('\ue098'),
		sli_reload('\ue099'),
		sli_settings('\ue09a'),
		sli_star('\ue09b'),
		sli_symbol_female('\ue09c'),
		sli_symbol_male('\ue09d'),
		sli_target('\ue09e'),
		sli_credit_card('\ue025'),
		sli_paypal('\ue608'),
		sli_social_tumblr('\ue00a'),
		sli_social_twitter('\ue009'),
		sli_social_facebook('\ue00b'),
		sli_social_instagram('\ue609'),
		sli_social_linkedin('\ue60a'),
		sli_social_pinterest('\ue60b'),
		sli_social_github('\ue60c'),
		sli_social_google('\ue60d'),
		sli_social_reddit('\ue60e'),
		sli_social_skype('\ue60f'),
		sli_social_dribbble('\ue00d'),
		sli_social_behance('\ue610'),
		sli_social_foursqare('\ue611'),
		sli_social_soundcloud('\ue612'),
		sli_social_spotify('\ue613'),
		sli_social_stumbleupon('\ue614'),
		sli_social_youtube('\ue008'),
		sli_social_dropbox('\ue00c'),
		sli_social_vkontakte('\ue618'),
		sli_social_steam('\ue620');

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
                typeface = new SimpleLineIcon();
            }
            return typeface;
        }
    }
}
