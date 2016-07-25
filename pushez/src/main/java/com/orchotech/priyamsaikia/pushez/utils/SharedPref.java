package com.orchotech.priyamsaikia.pushez.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Priyam on 25-07-2016.
 */
public class SharedPref {
    static SharedPreferences sp = null;

    static SharedPreferences getSP(Context context) {
        if (sp == null)
            return PreferenceManager.getDefaultSharedPreferences(context);
        else
            return sp;
    }

    public static void writeRegId(Context context, String regId) {
        getSP(context).edit().putString(AppConfig.reg_id, regId).commit();
    }

    public static String getRegId(Context context) {
        if (getSP(context) != null)
            return getSP(context).getString(AppConfig.reg_id, "");
        else
            return "";
    }



}