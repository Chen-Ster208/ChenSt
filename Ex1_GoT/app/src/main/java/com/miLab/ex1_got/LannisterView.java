package com.milab.ex1_got;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import static com.milab.ex1_got.R.id.recycler;
import com.milab.ex1_got.Adapter;


public class LannisterView extends AppCompatActivity {

    RecyclerView recyclerView;
    private final String[] names={"Tywin\nRIP", "Ceresei\nRIP", "Jaime\nRIP", "Tyrion", "Joffrey\nRIP", "Myrcella\nRIP", "Tommen\nRIP"};
    private final int[] images ={R.drawable.tywin, R.drawable.ceresei, R.drawable.jaime, R.drawable.tyrion, R.drawable.joffrey, R.drawable.myrcella, R.drawable.tommen};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lannister_view);
        recyclerView = (RecyclerView)findViewById(recycler);

        Adapter adapt = new Adapter(this, names, images);

        recyclerView.setAdapter(adapt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}