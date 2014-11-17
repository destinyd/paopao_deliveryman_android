package com.realityandapp.paopao_official_deliveryman.utils;

import android.graphics.Typeface;
import com.realityandapp.paopao_official_deliveryman.PaopaoOfficialDeliverymanApplication;

/**
 * Created by fushang318 on 2014/8/20.
 */
public class UiFont {
    final static public Typeface FONTAWESOME_FONT = Typeface.createFromAsset(
            PaopaoOfficialDeliverymanApplication.get_context().getAssets(),
            "fonts/fontawesome-webfont.ttf");
}
