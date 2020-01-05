package com.lazycoder.cakevpn;

import android.content.Context;
import android.content.SharedPreferences;

import com.lazycoder.cakevpn.model.Server;

import static com.lazycoder.cakevpn.Utils.getImgURL;

public class SharedPreference {

    private static final String APP_PREFS_NAME = "CakeVPNPreference";

    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;
    private Context context;

    private static final String SERVER_COUNTRY = "server_country";
    private static final String SERVER_FLAG = "server_flag";
    private static final String SERVER_OVPN = "server_ovpn";
    private static final String SERVER_OVPN_USER = "server_ovpn_user";
    private static final String SERVER_OVPN_PASSWORD = "server_ovpn_password";

    public SharedPreference(Context context) {
        this.appSharedPrefs = context.getSharedPreferences(APP_PREFS_NAME, Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
        this.context = context;
    }

    /**
     * Save server details
     * @param server
     */
    public void saveServer(Server server){
        prefsEditor.putString(SERVER_COUNTRY, server.getCountry());
        prefsEditor.putString(SERVER_FLAG, server.getFlagUrl());
        prefsEditor.putString(SERVER_OVPN, server.getOvpn());
        prefsEditor.putString(SERVER_OVPN_USER, server.getOvpnUserName());
        prefsEditor.putString(SERVER_OVPN_PASSWORD, server.getOvpnUserPassword());
        prefsEditor.commit();
    }

    public Server getServer() {
        Server server = new Server(
                appSharedPrefs.getString(SERVER_COUNTRY,"United States"),
                appSharedPrefs.getString(SERVER_FLAG,getImgURL(R.drawable.usa_flag)),
                appSharedPrefs.getString(SERVER_OVPN,"United States"),
                appSharedPrefs.getString(SERVER_OVPN_USER,"United States"),
                appSharedPrefs.getString(SERVER_OVPN_PASSWORD,"United States")

        );

        return server;
    }
}
