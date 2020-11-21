package com.miLab.ex1_got;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LannisterActivity extends WearableActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lannister);

//        RecyclerView recyclerView = (RecyclerView)findViewByid(R.id.list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Enables Always-on
        setAmbientEnabled();
    }
}