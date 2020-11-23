package com.milab.ex1_got;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static com.milab.ex1_got.R.id.recycler;

public class StarkAdapt extends RecyclerView.Adapter<StarkAdapt.MyViewHolder>{

    private String[] starkNames;
    private int[] starkPics;
    Context context;

    public StarkAdapt(Context con, String[] names, int[] pics){
        context = con;
        starkNames = names;
        starkPics = pics;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layInflate = LayoutInflater.from(context);
        View view = layInflate.inflate(R.layout.block_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.text.setText(starkNames[position]);
        holder.pic.setImageResource(starkPics[position]);
    }

    @Override
    public int getItemCount() {
        return starkNames.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        ImageView pic;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textView);
            pic = itemView.findViewById(R.id.imageView2);
        }
    }
}
