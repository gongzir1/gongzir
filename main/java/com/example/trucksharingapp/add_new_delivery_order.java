package com.example.trucksharingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trucksharingapp.data.DatabaseHelper;

public class add_new_delivery_order extends AppCompatActivity {
    Button createOrderBtn;
    TextView gtype1,gtype2,gtype3,gtype4,vtype1,vtype2,vtype3,vtype4;
    String goodtype="Food",weight,height,length,width,vehicle="mini-truck";
    EditText goodt,vehiclet,weight_et,height_et,length_et,width_et;
//    boolean otherGood,otherVehicle;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_delivery_order);
        Intent intent=getIntent();
        String date=intent.getStringExtra("date");
        String time=intent.getStringExtra("time");
        String location=intent.getStringExtra("location");
        createOrderBtn=findViewById(R.id.createOrderBtn);
        gtype1=findViewById(R.id.C1);
        gtype2=findViewById(R.id.C2);
        gtype3=findViewById(R.id.C3);
        gtype4=findViewById(R.id.C4);
        vtype1=findViewById(R.id.C5);
        vtype2=findViewById(R.id.C6);
        vtype3=findViewById(R.id.C7);
        vtype4=findViewById(R.id.C8);
        goodt=findViewById(R.id.E1);
        vehiclet=findViewById(R.id.E2);
        goodt.addTextChangedListener(textWatcher);
//        goodt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                gtype1.setTextColor(Color.BLACK);
//                gtype2.setTextColor(Color.BLACK);
//                gtype3.setTextColor(Color.BLACK);
//                gtype4.setTextColor(Color.BLACK);
//                goodt.setFocusableInTouchMode(true);
//                otherGood=true;
//            }
//        });

        vehiclet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vtype1.setTextColor(Color.BLACK);
                vtype2.setTextColor(Color.BLACK);
                vtype3.setTextColor(Color.BLACK);
                vtype4.setTextColor(Color.BLACK);
                vehiclet.setFocusableInTouchMode(true);
//                otherVehicle=true;
            }
        });
        gtype1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gtype1.setTextColor(Color.RED);
                gtype2.setTextColor(Color.BLACK);
                gtype3.setTextColor(Color.BLACK);
                gtype4.setTextColor(Color.BLACK);
                goodtype=gtype1.getText().toString();
//                otherGood=false;
            }
        });
        gtype2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gtype1.setTextColor(Color.BLACK);
                gtype2.setTextColor(Color.RED);
                gtype3.setTextColor(Color.BLACK);
                gtype4.setTextColor(Color.BLACK);
                goodtype=gtype2.getText().toString();
//                otherGood=false;
            }
        });
        gtype3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gtype1.setTextColor(Color.BLACK);
                gtype2.setTextColor(Color.BLACK);
                gtype3.setTextColor(Color.RED);
                gtype4.setTextColor(Color.BLACK);
                goodtype=gtype3.getText().toString();
//                otherGood=false;
            }
        });
        gtype4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gtype1.setTextColor(Color.BLACK);
                gtype2.setTextColor(Color.BLACK);
                gtype3.setTextColor(Color.BLACK);
                gtype4.setTextColor(Color.RED);
                goodtype=gtype4.getText().toString();
//                otherGood=false;
            }
        });

        vtype1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vtype1.setTextColor(Color.RED);
                vtype2.setTextColor(Color.BLACK);
                vtype3.setTextColor(Color.BLACK);
                vtype4.setTextColor(Color.BLACK);
                vehicle=vtype1.getText().toString();
//                otherVehicle=false;
            }
        });
        vtype2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vtype1.setTextColor(Color.BLACK);
                vtype2.setTextColor(Color.RED);
                vtype3.setTextColor(Color.BLACK);
                vtype4.setTextColor(Color.BLACK);
                vehicle=vtype2.getText().toString();
//                otherVehicle=false;
            }
        });
        vtype3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vtype1.setTextColor(Color.BLACK);
                vtype2.setTextColor(Color.BLACK);
                vtype3.setTextColor(Color.RED);
                vtype4.setTextColor(Color.BLACK);
                vehicle=vtype3.getText().toString();
//                otherVehicle=false;
            }
        });
        vtype4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vtype1.setTextColor(Color.BLACK);
                vtype2.setTextColor(Color.BLACK);
                vtype3.setTextColor(Color.BLACK);
                vtype4.setTextColor(Color.RED);
                vehicle=vtype4.getText().toString();
//                otherVehicle=false;
            }
        });


        db=new DatabaseHelper(this);
        createOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight_et=findViewById(R.id.AddWeight);
                weight=weight_et.getText().toString();
                height_et=findViewById(R.id.AddHeight);
                height=height_et.getText().toString();
                length_et=findViewById(R.id.AddLength);
                length=length_et.getText().toString();
                width_et=findViewById(R.id.AddWidth);
                width=width_et.getText().toString();
                Toast.makeText(add_new_delivery_order.this, goodtype, Toast.LENGTH_SHORT).show();
//                if(otherGood){goodtype=goodt.getText().toString();}
//                if(otherVehicle){vehicle=vehiclet.getText().toString();}
//
                if(TextUtils.isEmpty(weight_et.getText().toString())||TextUtils.isEmpty(height_et.getText().toString())||TextUtils.isEmpty(width_et.getText().toString())||TextUtils.isEmpty(length_et.getText().toString())){
                    Toast.makeText(add_new_delivery_order.this, "please fill all details", Toast.LENGTH_SHORT).show();
                }else {
//                    Order order=new Order();
//                    order.setDate(date);
//                    order.setTime(time);
//                    order.setLocation(location);
//                    order.setGoodtype(goodtype);
//                    order.setWeight(weight);
//                    order.setHeight(height);
//                    order.setLength(length);
//                    order.setWidth(width);
//                    order.setVehicle(vehicle);
//                    order.setUsername(MainActivity.user);
//                    long result=db.insertOrder(order);
//                    com.example.trucksharingapp.Order newOrder= new com.example.trucksharingapp.Order(goodtype,width);
//                    long result=db.insertOrder(newOrder);
//                    db.close();
//                    Boolean INSERT_OK;
//
//                    if(result!=-1){
//                        INSERT_OK = true;
//                    } else INSERT_OK = false;

                    Intent intent=new Intent(getApplicationContext(),My_orders_new.class);
                    intent.putExtra("goodtype",goodtype);
                    intent.putExtra("date",date);

                    startActivity(intent);
                }

            }
        });
    }
    TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            gtype1.setTextColor(Color.BLACK);
            gtype2.setTextColor(Color.BLACK);
            gtype3.setTextColor(Color.BLACK);
            gtype4.setTextColor(Color.BLACK);
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            goodtype=goodt.getText().toString();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}