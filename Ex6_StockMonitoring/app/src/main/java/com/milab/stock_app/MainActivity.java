package com.milab.stock_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.installations.FirebaseInstallations;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private static final String MY_SERVER = "http://192.168.1.15:3000/";
    private static final String TAG= "myTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView myText = (TextView) findViewById(R.id.textView);

//        RequestQueue queue = Volley.newRequestQueue(this);

        Button sendButton = (Button)findViewById(R.id.button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                String stockName = ((EditText) findViewById(R.id.givenSymbol)).getText().toString();
                handleServerSide(stockName);
            }
        });
    }

    // initializes firebase connection and sends request to server
    public void handleServerSide(String stockName){
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String token = instanceIdResult.getToken();
                try {
                    getThePrice(stockName, token);
                } catch (JSONException e) {
                    Log.e(TAG, "Error while requesting from server");
                    e.printStackTrace();
                }
            }
        });
        FirebaseInstallations.getInstance();
    }

    public void getThePrice (String stockName, String token) throws JSONException{
        JSONObject jsonReq = new JSONObject();
        jsonReq.put("token", token);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, MY_SERVER + "stocks/" + stockName,
                jsonReq, response -> Log.i(TAG, "Post to server done"),
                error -> Log.e(TAG, "Error posting to server")
        );
        //New request - need to adding it into the request queue
        FetcherRequestQueue.getInstance(this).getQueue().add(req);
    }
}