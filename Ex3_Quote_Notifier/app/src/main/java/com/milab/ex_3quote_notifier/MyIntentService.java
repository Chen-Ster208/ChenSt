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
//    com.milab.ex_3quote_notifier.MyIntentService.TAG

    MyReceiver myReceiver = new MyReceiver();

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_NOTIFY_FACT = "com.milab.ex_3quote_notifier.broadcast.";
    private static final String ACTION_NOTIFICATION = "com.milab.ex_3quote_notifier.action.notify";

    public MyIntentService() {
        super("MyIntentService");
        setIntentRedelivery(true);


    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "In onHandle");
        if (intent != null) {
            final String action = intent.getAction();
//            if (ACTION_NOTIFICATION.equals(action)) {
                try {
                    Log.d(TAG, "Trying");
                    handleActionNotification();
                } catch (PendingIntent.CanceledException e) {
                    Log.d(TAG, "catching");
                    e.printStackTrace();
                }
//            }
//            else {
//                throw new RuntimeException("Unknown action provided");
//            }
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
