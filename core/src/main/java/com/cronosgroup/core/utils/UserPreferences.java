package com.cronosgroup.core.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jorgesanmartin on 4/4/16.
 */
public class UserPreferences {

    private static final String APP = "USER_PREFERENCES";

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                APP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        return editor;
    }

    private static SharedPreferences getPreferences(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                APP, Context.MODE_PRIVATE);
        return sharedPref;
    }

    // Protected methods

    protected static void saveLong(Context context, String key, long value) {
        getEditor(context).putLong(key, value).commit();
    }

    protected static void saveString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();
    }

    protected static void saveInt(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();
    }

    protected static void saveBoolean(Context context, String key, boolean value) {
        getEditor(context).putBoolean(key, value).commit();
    }

    protected static String getString(Context context, String key) {
        return getPreferences(context).getString(key, "");
    }

    protected static int getInt(Context context, String key) {
        return getPreferences(context).getInt(key, 0);
    }

    protected static boolean getBoolean(Context context, String key) {
        return getPreferences(context).getBoolean(key, false);
    }

    protected static long getLong(Context context, String key) {
        return getPreferences(context).getLong(key, 0);
    }

    protected static boolean exist(Context context, String key) {
        return getPreferences(context).contains(key);
    }
}
