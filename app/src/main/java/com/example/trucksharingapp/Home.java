package com.example.trucksharingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements OrderAdapter.OnRowClickListener {
    RecyclerView rv_Order;
    OrderAdapter orderAdapter;
    List<Order> orderList=new ArrayList<>();
    ImageButton dropDownMenu;
    Button add_order;
    private static Integer[]newImageList={R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image};
    private static String[]newNameList={"order1","order2","order3","order4","order5","order6"};
    private static String[]newDetailList={"this is order1","this is order2","this is order3","this is order4","this is order5","this is order6"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rv_Order=findViewById(R.id.rv_order);
        orderAdapter=new OrderAdapter(orderList,Home.this,this);
        RecyclerView.LayoutManager vertical_manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv_Order.setAdapter(orderAdapter);
        rv_Order.setLayoutManager(vertical_manager);
        for(int i=0;i<newImageList.length;i++){
            Order order=new Order(newImageList[i],newNameList[i],newDetailList[i], R.drawable.changetask);
            orderList.add(order);
        }
        Intent intent = getIntent();
        String username = intent.getStringExtra("user");
        dropDownMenu=findViewById(R.id.imageButton);
        dropDownMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(Home.this, dropDownMenu);
                popup.getMenuInflater()
                        .inflate(R.menu.menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals("Home")){
                            Toast.makeText(getApplicationContext(), "home",Toast.LENGTH_SHORT).show();
                        }
                        else if (item.getTitle().equals("My orders")){
                            Intent intentorder= new Intent(getApplicationContext(),My_orders_new.class);
                            intentorder.putExtra("user", username);
                            startActivity(intentorder);
                            Toast.makeText(getApplicationContext(), "my orders",Toast.LENGTH_SHORT).show();
                        }
                        else if(item.getTitle().equals("Account")){
                           // Toast.makeText(getApplicationContext(), "account",Toast.LENGTH_SHORT).show();
                        Intent intentaccount=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intentaccount);
                        }
                        return true;
                    }
                });

                popup.show();
            }
        });
        add_order=findViewById(R.id.btn_add_order);
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
        Intent intentdetail=new Intent(getApplicationContext(),Order_details.class);
        startActivity(intentdetail);
    }
}