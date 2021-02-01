package com.milab.stock_app;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

public class MyServiceFireBase extends FirebaseMessagingService {

    private static final String TAG = "Service tag";
    private static final String MY_SERVER = "http://192.168.1.15:3000/";

    private RequestQueue queue;

    static String CHANNEL = "MY_CHANNEL";
    static int notyID = 10;

    @Override
    public void onCreate(){
        super.onCreate();
        queue = Volley.newRequestQueue(this);
    }

    @Override
    public void onNewToken(@NonNull String token){
        super.onNewToken(token);
        registerToken(token);
    }

    //registers token to firebase when a new one is created
    public void registerToken(String token){
        JSONObject requestObject = new JSONObject();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, MY_SERVER + "token/" + token,
                requestObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "Token saved successfully");
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Failed to save token - " + error);
                    }
                });
        FetcherRequestQueue.getInstance(this).getQueue().add(req);
    }

    @Override
    public void onMessageReceived(RemoteMessage rm){
        //make sure that the message is not empty
        if (rm.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + rm.getData());
        }

        //if message has a 'notyfication' type, send it to user
        if (rm.getNotification() != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    //Push notification with the information that sent from firebase
                    sendNotification(rm.getNotification().getBody());
                }
            });
        }
    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        //a notification channel is needed
        createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL)
                .setContentTitle("HOT HOT UPDATE")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notyID, builder.build());
    }


    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Ex6_channel";

            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(CHANNEL, name, importance);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}