package com.makein.client.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Sessions {

    private SharedPreferences pref;
    private Editor editor;
    private Context _context;
    private int private_mode = 0;
    private static final String PREF_NAME = "MakeIn_App_client";

    public Sessions(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, private_mode);
        editor = pref.edit();
    }

    public static void setUserObject(Context c, String userObject, String key) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(c);
        Editor editor = pref.edit();
        editor.putString(key, userObject);
        editor.commit();
    }

    public static String getUserObject(Context ctx, String key) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(ctx);
        String userObject = pref.getString(key, null);
        return userObject;
    }

    public static void removeUserObject(Context ctx, String key) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
        settings.edit().remove(key).commit();
    }
    // --------------------------------------------------------------------------------------
}
