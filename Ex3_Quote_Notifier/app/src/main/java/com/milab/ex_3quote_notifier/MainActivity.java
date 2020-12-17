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
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> FACTS = new ArrayList<String>();
    public static String CHANNEL_ID = "MyChannel";
    public static final String DID_YOU_KNOW = "Did you know?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            scanFacts();
        } catch (IOException e) {
            e.printStackTrace();
        }

        createNotificationChannel();

        Button notify = (Button)findViewById(R.id.now_button);
        notify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                int factNum = (int) (Math.random() * MainActivity.FACTS.size());
                NotificationCompat.Builder builder = new NotificationCompat.Builder(view.getContext(), MainActivity.CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(DID_YOU_KNOW)
                        .setContentText(MainActivity.FACTS.get(factNum))
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(MainActivity.FACTS.get(factNum)));

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

            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


    }

    private void scanFacts() throws IOException {
        String line;
        InputStream is = this.getResources().openRawResource(R.raw.facts);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        while ((line = reader.readLine()) != null) {

            FACTS.add(line);
        }
        is.close();
    }
}