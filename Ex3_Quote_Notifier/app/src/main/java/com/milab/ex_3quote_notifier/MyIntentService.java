package com.milab.ex_3quote_notifier;

import android.app.AlarmManager;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;


public class MyIntentService extends IntentService {

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
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_NOTIFICATION.equals(action)) {
                handleActionNotification();
            } else {
                throw new RuntimeException("Unknown action provided");
            }
        }
    }


    private void handleActionNotification() {
        Intent intent = new Intent();
        intent.setAction(String.valueOf(MyReceiver.class));
        intent.putExtra("data","My Quote");
        sendBroadcast(intent);
        AlarmManager myAlarm = new AlarmManager();
        myAlarm.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                0,
                1000*10,
                intent);

    }
}
