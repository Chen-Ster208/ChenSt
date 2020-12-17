package com.milab.ex1_got;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import static com.milab.ex1_got.R.id.recycler;
import com.milab.ex1_got.Adapter;

public class StarkView extends AppCompatActivity {

    RecyclerView recyclerView;
    private final String[] names = {"Benjen\nRIP", "Lyanna\nRIP", "Eddard\nRIP", "Catelyn\nRIP", "Rob\nRIP", "Jon Snow\nRIP(ish)", "Sansa", "Bran", "Arya", "Rickon\nRIP"};
    private final int[] images = {R.drawable.benjen, R.drawable.lyanna, R.drawable.eddard, R.drawable.catelyn, R.drawable.robb, R.drawable.jonsnow, R.drawable.sansa, R.drawable.bran, R.drawable.arya, R.drawable.rickon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stark_view);
        recyclerView = (RecyclerView)findViewById(recycler);

        Adapter adapt = new Adapter(this, names, images);

        recyclerView.setAdapter(adapt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}