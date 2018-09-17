package com.example.shaochengyang.recycleviewproject;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyRecycleViewAdaptor extends RecyclerView.Adapter<MyRecycleViewAdaptor.MyViewHolder>{
    List<Movie> movieList;
    public  MyRecycleViewAdaptor(List<Movie> list){ //pass list from main activity
        movieList = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie movies = movieList.get(position);
        holder.title.setText(movies.getTitle());
        holder.gener.setText(movies.getGenre());
        holder.year.setText(movies.getYear());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, gener, year;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView);
            gener = itemView.findViewById(R.id.generText);
            year = itemView.findViewById(R.id.yearText);
        }
    }


}
