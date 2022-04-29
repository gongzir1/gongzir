package com.example.a51c;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapterV extends RecyclerView.Adapter<RecyclerViewAdapterV.ViewHolder>{

    private List<NewsData> newsDataList;
    private Context hContext;

    public RecyclerViewAdapterV(List newsDataList, Context hContext){
        this.newsDataList = newsDataList;
        this.hContext = hContext;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row_h, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterV.ViewHolder holder, int position) {
        holder.vButton.setId(newsDataList.get(position).getnewsImage().getId());
        holder.title.setText(newsDataList.get(position).getnewsName());

    }

    @Override
    public int getItemCount() {
        return newsDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageButton vButton;
        public TextView title;
        public TextView body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vButton = itemView.findViewById(R.id.news_row_button1);
            title = itemView.findViewById(R.id.textViewT);
//            body = itemView.findViewById(R.id.textViewB);
        }


    }

}
