package com.milab.ex_3quote_notifier;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import static com.milab.ex_3quote_notifier.R.drawable.cn_backgrnd;

public class MyReceiver extends BroadcastReceiver {
    public static final String TAG = "Myreceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Receiving!!!");
        int factNum = (int) (Math.random() * 102);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Did you know?")
                .setContentText(MainActivity.FACTS[factNum])
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(MainActivity.FACTS[factNum]));

        NotificationManager notiMan = (NotificationManager)context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        int notifyID = 1;
        notiMan.notify(notifyID, builder.build());

    }


}