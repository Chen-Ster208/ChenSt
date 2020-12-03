package com.milab.ex_3quote_notifier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    public static String CHANNEL_ID = "MyChannel";
//    IntentFilter myFilter;
//    MyReceiver myReceiver = new MyReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        myFilter = new IntentFilter(String.valueOf(MyReceiver.class));
//        registerReceiver(myReceiver, myFilter);

        createNotificationChannel();

        Button notify = (Button)findViewById(R.id.now_button);
        notify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                NotificationCompat.Builder builder = new NotificationCompat.Builder(view.getContext(), CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Did you know?")
                        .setContentText("Vualla!!! Notification!")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);


                NotificationManager notiMan = (NotificationManager)view.getContext()
                        .getSystemService(Context.NOTIFICATION_SERVICE);

                int notifyID = 1;
                notiMan.notify(notifyID, builder.build());
            }
        });


        Button timerButton = (Button)findViewById(R.id.timer_button);
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), MyIntentService.class);
                startService(intent);
            }
        });
    }




    private void createNotificationChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Ex3_channel";
//            String description = "Just some description";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

//    private void setReceiver() {
//        MyReceiver mr = new MyReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(___);
//
//        LocalBroadcastManager.getInstance(this).registerReceiver(MyReceiver, intentFilter);
//    }

//    @Override
//    protected void onStart() {
//        setReceiver();
//        super.onStart();
//    }

//    @Override
//    protected void onStop() {
//        unregisterReceiver(MyReceiver);
//        super.onStop();
//    }
}