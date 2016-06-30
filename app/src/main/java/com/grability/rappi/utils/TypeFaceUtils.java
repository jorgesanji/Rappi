package com.grability.rappi.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

/**
 * Font utils.
 */
public class TypeFaceUtils {

    private static final String TAG = TypeFaceUtils.class.getSimpleName();

    // Public methods

    /**
     * @param context
     * @param font
     * @return
     */
    public static Typeface getFont(Context context, String font) {
        Typeface tf;
        try {
            tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + font);
            return tf;
        } catch (Exception e) {
            Log.e(TAG, "Could not get typeface: " + e.getMessage());
        }
        return null;
    }

    public static Typeface getFontWithFlag(Context context, int type) {
        switch (type) {
            case 0:
                return getRegularFont(context);
            case 1:
                return getBoldFont(context);
            case 2:
                return getLightFont(context);
            case 3:
                return getSemiBoldFont(context);
        }
        return null;
    }

    /**
     * @return
     */
    public static Typeface getRegularFont(Context context) {
        return getFont(context, "OpenSans-Regular.ttf");
    }

    /**
     * @return
     */
    public static Typeface getBoldFont(Context context) {
        return getFont(context, "OpenSans-Bold.ttf");
    }

    /**
     * @return
     */
    public static Typeface getLightFont(Context context) {
        return getFont(context, "OpenSans-Light.ttf");
    }

    /**
     * @return
     */
    public static Typeface getSemiBoldFont(Context context) {
        return getFont(context, "OpenSans-Semibold.ttf");
    }
}
