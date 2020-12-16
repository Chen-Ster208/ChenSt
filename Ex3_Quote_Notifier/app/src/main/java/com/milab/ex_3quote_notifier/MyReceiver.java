package com.milab.ex_3quote_notifier;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int factNum = (int) (Math.random() * MainActivity.FACTS.size());

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(MainActivity.DID_YOU_KNOW)
                .setContentText(MainActivity.FACTS.get(factNum))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(MainActivity.FACTS.get(factNum)));

        NotificationManager notiMan = (NotificationManager)context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        int notifyID = 1;
        notiMan.notify(notifyID, builder.build());
    }
}