package com.milab.ex_3quote_notifier;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;


public class MyIntentService extends IntentService {
    private static final String TAG = "MyService";
    public static final String NOTIFY = "com.milab.ex_3quote_notifier.notify";

    MyReceiver myReceiver = new MyReceiver();

    public MyIntentService() {
        super("MyIntentService");
        setIntentRedelivery(true);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "In onHandle");
        if (intent != null) {
            final String action = intent.getAction();
                try {
                    Log.d(TAG, "Trying");
                    handleActionNotification();
                } catch (PendingIntent.CanceledException e) {
                    Log.d(TAG, "catching");
                    e.printStackTrace();
                }
        }
    }


    private void handleActionNotification() throws PendingIntent.CanceledException {
        Intent intent = new Intent(this, MyReceiver.class);
        intent.setAction(NOTIFY);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        pendingIntent.send();

        AlarmManager myAlarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        myAlarm.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                    SystemClock.elapsedRealtime(),
                                    1000 * 60 * 5,
                                    pendingIntent);
    }
}
