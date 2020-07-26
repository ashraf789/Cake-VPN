package de.blinkt.openvpn.utils;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import de.blinkt.openvpn.core.OpenVPNService;

public class TotalTraffic {

    public static final String TRAFFIC_ACTION = "traffic_action";

    public static final String DOWNLOAD_ALL = "download_all";
    public static final String DOWNLOAD_SESSION = "download_session";
    public static final String UPLOAD_ALL = "upload_all";
    public static final String UPLOAD_SESSION = "upload_session";

    public static long inTotal;
    public static long outTotal;


    public static void calcTraffic(Context context, long in, long out, long diffIn, long diffOut) {
        List<String> totalTraffic = getTotalTraffic(context, diffIn, diffOut);

        Intent traffic = new Intent();
        traffic.setAction(TRAFFIC_ACTION);
        traffic.putExtra(DOWNLOAD_ALL, totalTraffic.get(0));
        traffic.putExtra(DOWNLOAD_SESSION, OpenVPNService.humanReadableByteCount(in, false, context.getResources()));
        traffic.putExtra(UPLOAD_ALL, totalTraffic.get(1));
        traffic.putExtra(UPLOAD_SESSION, OpenVPNService.humanReadableByteCount(out, false, context.getResources()));

        context.sendBroadcast(traffic);
    }

    public static List<String> getTotalTraffic(Context context) {
        return getTotalTraffic(context, 0, 0);
    }

    public static List<String> getTotalTraffic(Context context, long in, long out) {
        List<String> totalTraffic = new ArrayList<String>();

        if (inTotal == 0)
            inTotal = PropertiesService.getDownloaded(context);

        if (outTotal == 0)
            outTotal = PropertiesService.getUploaded(context);

        inTotal = inTotal + in;
        outTotal = outTotal + out;

        totalTraffic.add(OpenVPNService.humanReadableByteCount(inTotal, false, context.getResources()));
        totalTraffic.add(OpenVPNService.humanReadableByteCount(outTotal, false, context.getResources()));

        return totalTraffic;
    }

    public static void saveTotal(Context context) {
        if (inTotal != 0)
            PropertiesService.setDownloaded(context, inTotal);

        if (outTotal != 0)
            PropertiesService.setUploaded(context, outTotal);
    }

    public static void clearTotal(Context context) {
        inTotal = 0;
        PropertiesService.setDownloaded(context, inTotal);
        outTotal = 0;
        PropertiesService.setUploaded(context, outTotal);
    }

}
