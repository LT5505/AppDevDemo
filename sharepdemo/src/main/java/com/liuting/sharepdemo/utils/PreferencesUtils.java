package com.liuting.sharepdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @Title: PreferencesUtils
 * @Package: com.liuting.sharepdemo.utils
 * @Description: 内存信息存储工具类
 * @author: liuting
 * @Date: 2017/5/2 14:02
 */

public class PreferencesUtils {
    public static String PREFERENCE_NAME = "Pref_Common";

    /**
     * @author: liuting
     * @date: 2017/5/2 14:03
     * @Title: putString
     * @Description: 存储 String
     * @param: context     Context 上下文
     * @param: key         键
     * @param: value       值
     * @return: boolean    是否存储成功，true 表示成功，false 表示失败
     * @throws
     */
    public static boolean putString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * @author: liuting
     * @date: 2017/5/2 14:06
     * @Title: getString
     * @Description: 无默认值时获取到相应的值
     * @param: context            Context
     * @param: key                键
     * @return: java.lang.String  键所对应的值
     * @throws
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * @author: liuting
     * @date: 2017/5/2 14:06
     * @Title: getString
     * @Description: 有默认值时根据键获取相应的值
     * @param: context                 Context
     * @param: key                     键
     * @param: defaultValue            默认值
     * @return: java.lang.String       键所对应的值
     * @throws
     */
    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    /**
     * @author: liuting
     * @date: 2017/5/2 14:09
     * @Title: putInt
     * @Description:  存储 int 类型的值
     * @param: context     Context
     * @param: key         键
     * @param: value       值
     * @return: boolean    是否存储成功 true 表示成功，false 表示失败
     * @throws
     */
    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public static long getLong(Context context, String key) {
        return getLong(context, key, -1);
    }

    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }

    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1);
    }

    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }

    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }

}
