package com.milab.stock_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private static final String TAG= "myTAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView myText = (TextView) findViewById(R.id.textView);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.google.com";
        String myURL = "http://192.168.1.15:3000/";

//        StringRequest stringReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
        StringRequest stringReq = new StringRequest(Request.Method.GET, myURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                textView.setText("Response is: "+ response.substring(0,500));
                Log.d(TAG, response.substring(0, 50));
                myText.setText("You got it!!");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "didn't work!!!");
            }
        });

        Button sendButton = (Button)findViewById(R.id.button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                queue.add(stringReq);
            }
        });
    }
}