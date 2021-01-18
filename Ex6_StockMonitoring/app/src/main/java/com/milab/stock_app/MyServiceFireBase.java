package com.milab.stock_app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessagingService;

import org.json.JSONObject;

public class MyServiceFireBase extends FirebaseMessagingService {

    private static final String TAG = "Service tag";
    private static final String MY_SERVER = "http://192.168.1.15:3000/";

    private RequestQueue queue;

    static int CHANNEL = 10;
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
}