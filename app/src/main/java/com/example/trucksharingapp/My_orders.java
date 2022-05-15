package com.example.trucksharingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.TransactionTooLargeException;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.trucksharingapp.data.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class My_orders extends AppCompatActivity implements OrderAdapter.OnRowClickListener {
    RecyclerView rv_Order;
    OrderAdapter orderAdapter;
    List<Order> orderList=new ArrayList<>();
    ImageButton dropDownMenu;
    Button add_order;
    DatabaseHelper db;
    private static Integer[]newImageList={R.drawable.ic_launcher_foreground,R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        db=new DatabaseHelper(My_orders.this);

        orderList=db.fetchAllOrder(MainActivity.user);
        Log.d("?????", "onClick: ");
        for(int i=0;i<orderList.size();i++) {
            String title = orderList.get(i).getGoodtype() + "to" + orderList.get(i).getLocation();
            String detail = "Date:" + orderList.get(i).getDate() + "Time:" + orderList.get(i).getTime();
            Log.d("title", title);
            orderList.get(i).setTitle(title);
            orderList.get(i).setDetail(detail);
            orderList.get(i).setImage(R.drawable.image);
        }
        rv_Order=findViewById(R.id.rv_order);
        orderAdapter=new OrderAdapter(orderList,My_orders.this,this);
        RecyclerView.LayoutManager vertical_manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv_Order.setAdapter(orderAdapter);
        rv_Order.setLayoutManager(vertical_manager);


        dropDownMenu=findViewById(R.id.imageButton);
        dropDownMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(My_orders.this, dropDownMenu);
                popup.getMenuInflater()
                        .inflate(R.menu.menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals("Home")){
//
                            Intent intent1= new Intent(getApplicationContext(),Home.class);
                            startActivity(intent1);
                        }
                        else if (item.getTitle().equals("My orders")){
                            Intent intent= new Intent(getApplicationContext(),My_orders.class);
                            startActivity(intent);
                        }

                        return true;
                    }
                });

                popup.show();
            }
        });
        add_order=findViewById(R.id.btn_add_order2);
        add_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentadd=new Intent(getApplicationContext(),Add_order.class);
                startActivity(intentadd);
            }
        });

    }

    @Override
    public void onItemClick(int id) {
        Intent intent=new Intent(My_orders.this,Order_details.class);
        intent.putExtra("user",orderList.get(id).getUsername());
        Log.d("xyz", orderList.get(id).getTime());
        intent.putExtra("time",orderList.get(id).getTime());
        intent.putExtra("goodtype",orderList.get(id).getGoodtype());
        intent.putExtra("weight",orderList.get(id).getWeight());
        intent.putExtra("height",orderList.get(id).getHeight());
        intent.putExtra("width",orderList.get(id).getWidth());
//        intent.putExtra("length",orderList.get(id).getLength());
//        intent.putExtra("quanlity",orderList.get(id).get)
        startActivity(intent);
    }
}