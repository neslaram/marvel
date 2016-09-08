package com.example.neslaram.marvel.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;

import com.example.neslaram.marvel.R;
import com.example.neslaram.marvel.data.model.Character;
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

    public static void createNotification(int nId, Character item, Context context) {

        String characterName = item.getName();
        String body = String.format(context.getResources().getString(R.string.number_of_comics), characterName, item.getComics().getAvailable());

        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Contants.KEY_CHARACTER_ID, nId);
        intent.putExtra(Contants.KEY_CHARACTER_NAME, characterName);
        intent.putExtra(Contants.KEY_CHARACTER_IMG, item.getThumbnail());


        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, flags);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(characterName)
                        .setContentText(body)
                        .setContentIntent(pIntent)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                        .setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(nId, mBuilder.build());
    }
}
