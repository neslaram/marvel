package com.example.neslaram.marvel.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;

import com.example.neslaram.marvel.R;
import com.example.neslaram.marvel.ui.detail.DetailActivity;

/**
 * Created by desarrollo on 9/6/16.
 */
public class Utils {
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();

    }


    public static void createNotification(int nId, String title, String body, Bundle bundle, Context context, Bitmap largeIcon) {

        Intent intent = new Intent(context, DetailActivity.class);
        if (!bundle.isEmpty()) {
            intent.putExtras(bundle);
        }

        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, flags);


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setLargeIcon(largeIcon)
                        .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                        .setContentTitle(title)
                        .setContentText(body)
                        .setContentIntent(pIntent)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                        .setAutoCancel(true);

        NotificationManagerCompat.from(context).notify(nId, mBuilder.build());
    }
}
