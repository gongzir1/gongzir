package com.example.restaurantmapapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlertsAdapter extends RecyclerView.Adapter<AlertsAdapter.AlertsViewHolder> {

    Context context;
    ArrayList<Alert> AlertsList;
    ItemClickListener ClickListener;

    //constructor for the adapter
    public AlertsAdapter(@NonNull Context context, ArrayList<Alert> alertsList, ItemClickListener ClickListener) {
        this.context = context;
        AlertsList = alertsList;
        this.ClickListener = ClickListener;
    }

    @NonNull
    @Override
    public AlertsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //the inflater instantiates the layout xml
        View ItemView = LayoutInflater.from(context).inflate(R.layout.alerts, parent, false);
        return new AlertsViewHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlertsViewHolder holder, int position) {
        //populate the item with the relevant information
        holder.LabelAlertListing.setText(AlertsList.get(position).getName()+" "+AlertsList.get(position).getLostOrFound()+" "+AlertsList.get(position).getDescription()+" on "+AlertsList.get(position).getDate()+" ... ");

        //this is part of the onclick event to open an alert fragment that was taken from the video
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickListener.onItemClick(AlertsList.get(holder.getAdapterPosition()));
            }
        });
    }

    //this is for populating the recycler view with the right number of items
    @Override
    public int getItemCount() {
        return AlertsList.size();
    }

    public class AlertsViewHolder extends RecyclerView.ViewHolder {
        //create the variables for the ui elements of the item and link them to the ui elements
        TextView LabelAlertListing;
        public AlertsViewHolder(@NonNull View itemView) {
            super(itemView);
            LabelAlertListing =itemView.findViewById(R.id.LabelAlertListing);
        }
    }

    //this is part of the code for the item click taken from the video
    public interface ItemClickListener{
        public void onItemClick(Alert alert);
    }
}
