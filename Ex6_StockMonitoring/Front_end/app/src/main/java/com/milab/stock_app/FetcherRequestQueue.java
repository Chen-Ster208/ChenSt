package com.milab.stock_app;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class FetcherRequestQueue {
    private static FetcherRequestQueue fetch;
    private RequestQueue queue;

    public  FetcherRequestQueue(Context context) {
        queue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public RequestQueue getQueue(){
        return queue;
    }

    public static synchronized FetcherRequestQueue getInstance(Context context){
        if ( fetch == null){
            fetch = new FetcherRequestQueue(context);
        }
        return fetch;
    }

}
