package de.blinkt.openvpn.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class PropertiesService {

    private static final String DOWNLOADED_DATA_KEY = "downloaded_data";
    private static final String UPLOADED_DATA_KEY = "uploaded_data";
    private static SharedPreferences prefs;

    private synchronized static SharedPreferences getPrefs(Context context) {
        if (prefs == null) {
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return prefs;
    }

    public static long getDownloaded(Context context) {
        return getPrefs(context).getLong(DOWNLOADED_DATA_KEY, 0);
    }

    public static void setDownloaded(Context context, long count) {
        getPrefs(context).edit().putLong(DOWNLOADED_DATA_KEY, count).apply();
    }

    public static long getUploaded(Context context) {
        return getPrefs(context).getLong(UPLOADED_DATA_KEY, 0);
    }

    public static void setUploaded(Context context, long count) {
        getPrefs(context).edit().putLong(UPLOADED_DATA_KEY, count).apply();
    }
}
