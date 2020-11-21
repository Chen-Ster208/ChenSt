package com.milab.ex1_got;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import static com.milab.ex1_got.R.id.recycler;

public class LannisterView extends AppCompatActivity {

    RecyclerView recyclerView;
    private final String[] names={"Tywin\nRIP", "Ceresei\nRIP", "Jaime\nRIP", "Tyrion", "Joffrey\nRIP", "Myrcella\nRIP", "Tommen\nRIP"};
    private final int[] images ={R.drawable.Tywin, R.drawable.Ceresei, R.drawable.Jaime, R.drawable.Tyrion, R.drawable.Joffrey, R.drawable.Myrcella, R.drawable.Tommen};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lannister_view);

        recyclerView = (RecyclerView)findViewById(recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}