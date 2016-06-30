package com.grability.rappi.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jorgesanmartin on 3/31/16.
 */
public class UserPreferences {

    private static final String APP = "USER_PREFERENCES";
    private static final String USER_DURATION = "USER_DURATION";

    public static float getDuration(Context context) {
        return getFloat(context, USER_DURATION, 1.0f);
    }

    public static void saveDuration(Context context, float duration) {
        saveFloat(context, USER_DURATION, duration);
    }

    // Private methods

    private static void saveLong(Context context, String key, long value) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                APP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    private static void saveString(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                APP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private static void saveInt(Context context, String key, int value) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                APP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private static void saveFloat(Context context, String key, float value) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                APP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    private static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                APP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(APP, Context.MODE_PRIVATE);
        return sharedPref.getString(key, defaultValue);
    }

    private static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(APP, Context.MODE_PRIVATE);
        return sharedPref.getInt(key, defaultValue);
    }

    private static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(APP, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, defaultValue);
    }

    private static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(APP, Context.MODE_PRIVATE);
        return sharedPref.getLong(key, defaultValue);
    }

    private static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(APP, Context.MODE_PRIVATE);
        return sharedPref.getFloat(key, defaultValue);
    }

    private static boolean exist(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(APP, Context.MODE_PRIVATE);
        return sharedPref.contains(key);
    }
}
