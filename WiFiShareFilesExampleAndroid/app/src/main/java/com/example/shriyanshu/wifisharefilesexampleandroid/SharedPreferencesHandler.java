package com.example.shriyanshu.wifisharefilesexampleandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SharedPreferencesHandler {

	public static SharedPreferences getSharedPreferences(Context ctx) {
		return PreferenceManager.getDefaultSharedPreferences(ctx);
	}

	public static void setStringValues(Context ctx, String key,
									   String DataToSave) {
		Editor editor = getSharedPreferences(ctx).edit();
		editor.putString(key, DataToSave);
		editor.commit();
	}

	public static String getStringValues(Context ctx, String key) {
		return getSharedPreferences(ctx).getString(key, null);
	}


}