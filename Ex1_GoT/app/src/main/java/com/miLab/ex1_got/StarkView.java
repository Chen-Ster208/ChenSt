package com.milab.ex1_got;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import static com.milab.ex1_got.R.id.recycler;

public class StarkView extends AppCompatActivity {

    RecyclerView recyclerView;
    private final String[] names = {"Benjen\nRIP", "Lyanna\nRIP", "Eddard\nRIP", "Catelyn\nRIP", "Rob\nRIP", "Jon Snow\nRIP(ish)", "Sansa", "Bran", "Arya", "Rickon\nRIP"};
    private final int[] images = {R.drawable.Benjen, R.drawable.Lyanna, R.drawable.Eddard, R.drawable.Catelyn, R.drawable.Robb, R.drawable.JonSnow, R.drawable.Sansa, R.drawable.Bran, R.drawable.Arya, R.drawable.Rickon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stark_view);

        recyclerView = (RecyclerView)findViewById(recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}