package com.example.a51c;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapterH extends RecyclerView.Adapter<RecyclerViewAdapterH.ViewHolder>{

    private List<NewsData> newsDataList;
    private Context hContext;

    public RecyclerViewAdapterH(List newsDataList, Context hContext){
        this.newsDataList = newsDataList;
        this.hContext = hContext;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterH.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterH.ViewHolder holder, int position) {
        //holder.hButton = newsDataList.get(position).getnewsImage();
    }

    @Override
    public int getItemCount() {
        return newsDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageButton hButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //hButton = itemView.findViewById(R.id.news_row_button1);
        }


    }

}
