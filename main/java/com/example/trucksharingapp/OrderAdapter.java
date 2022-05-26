package com.example.trucksharingapp;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<Order>orderList;
    private Context context;
    private OnRowClickListener listener;
    public OrderAdapter(List<Order>orderList,Context context,OnRowClickListener listener){
        this.orderList=orderList;
        this.context=context;
        this.listener=listener;
    }
    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.order,parent,false);
        return new OrderViewHolder(itemView,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {
        holder.image.setImageResource(orderList.get(position).getImage());
        holder.tv_title.setText(orderList.get(position).getTitle());
        holder.tv_detail.setText(orderList.get(position).getDetail());
        holder.order=orderList.get(position);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
    public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView image;
        public TextView tv_title,tv_detail;
        public OnRowClickListener onRowClickListener;
        public Button imageButton;
        public Order order;
        public OrderViewHolder(@NonNull View itemView,OnRowClickListener onRowClickListener) {
            super(itemView);
            image=itemView.findViewById(R.id.image_order);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_detail=itemView.findViewById(R.id.tv_detail);
            this.onRowClickListener=onRowClickListener;
            itemView.setOnClickListener(this);
            imageButton=itemView.findViewById(R.id.image_order_btn);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent shareIntent =new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    String shareBody=tv_title.getText().toString();
                    shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                    context.startActivity(Intent.createChooser(shareIntent,"Shareusing"));

                }
            });


        }

        @Override
        public void onClick(View view) {
            onRowClickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnRowClickListener{
        void onItemClick(int id);
    }
}
