package com.example.learningsql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningsql.model.YoutubeLink;

import java.util.List;

public class YoutubelinkAdapter extends RecyclerView.Adapter<YoutubelinkAdapter.YoutubelinkViewHolder> {
    private List<YoutubeLink> linkList;
    private Context context;
    private OnRowClickListener listener;

    public YoutubelinkAdapter(List<YoutubeLink> linkList, Context context, OnRowClickListener listener) {
        this.linkList = linkList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public YoutubelinkAdapter.YoutubelinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.link,parent,false);
        return new YoutubelinkViewHolder(itemView,listener);

    }

    @Override
    public void onBindViewHolder(@NonNull YoutubelinkAdapter.YoutubelinkViewHolder holder, int position) {
        holder.textView.setText(linkList.get(position).getLink());
    }

    @Override
    public int getItemCount() {
        return linkList.size();
    }
    public class YoutubelinkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        public OnRowClickListener onRowClickListener;
        public YoutubelinkViewHolder(@NonNull View itemView,OnRowClickListener listener) {

            super(itemView);
            this.onRowClickListener=listener;
            itemView.setOnClickListener(this);
            textView=itemView.findViewById(R.id.link);
        }

        @Override
        public void onClick(View view) {
            onRowClickListener.onItemClick(linkList.get(getAdapterPosition()).getId());
        }
    }
    public interface OnRowClickListener{
        void onItemClick(int id);
    }
}
