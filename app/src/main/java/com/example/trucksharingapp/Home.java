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
    private static Integer[]newImageList={R.drawable.new1,R.drawable.new2,R.drawable.new3,R.drawable.new4,R.drawable.new5,R.drawable.new6};
    private static String[]newNameList={"aaaaaaaaaa","bbbbbbbbb","ccccccccc","dddddddddd","eeeeeeeee","fffffffffff"};
    private static String[]newDetailList={"111111111111111111111111111111111111111111111111111111111111111111111","2222222222222222222222222222222222222222222222222222222222222222222","3333333333333333333333333333333333333333333333333333333333333333333333333333333","444444444444444444444444444444444444444444444444444444444","55555555555555555555555555555555555555555555555","6666666666666666666666666666666666666666666"};
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
//
                            Toast.makeText(getApplicationContext(), "1",Toast.LENGTH_SHORT).show();
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

    }
}