package com.example.trucksharingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Order_details extends AppCompatActivity {
    TextView usertv,timetv,goodtypetv,weighttv,heighttv,lengthtv,widthtv,quantitytv,totv;
    ImageView imageView;
    Button detailBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Intent intent=getIntent();
//        String user="From:"+MainActivity.user;
//        String time="Pick up time:"+intent.getStringExtra("time");
//        String weight="Weight:"+intent.getStringExtra("weight");
//        String width="Width:"+intent.getStringExtra("width");
//        String length="Length:"+intent.getStringExtra("length");
//        String goodtype="Type:"+intent.getStringExtra("goodtype");
//        String height="Height:"+intent.getStringExtra("height");
//        String quantity="Quantity:"+intent.getStringExtra("quantity");
        String from="deakin";
        String to="city";
        String time="Pick up time:"+"12:00";
        String weight="Weight:"+"10kg";
        String width="Width:"+"10m";
        String length="Length:"+"10m";
        String goodtype="Type:"+"furniture";
        String height="Height:"+"5m";
        String quantity="Quantity:"+"3";

        imageView=findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.image);
        usertv=findViewById(R.id.Textviewa);
        totv=findViewById(R.id.Textviewb);
        timetv=findViewById(R.id.Textviewc);
        weighttv=findViewById(R.id.Textviewe);
        widthtv=findViewById(R.id.Textviewg);
//        lengthtv=findViewById(R.id.Textviewi);
        goodtypetv=findViewById(R.id.Textviewf);
        heighttv=findViewById(R.id.Textviewh);
//        quantitytv.findViewById(R.id.Textviewj);
        usertv.setText("From:"+from);
        totv.setText("to:"+to);
        timetv.setText(time);
        weighttv.setText(weight);
        widthtv.setText(width);
//        lengthtv.setText(length);
        goodtypetv.setText(goodtype);
        heighttv.setText(height);
//        quantitytv.setText(quantity);
        detailBtn=findViewById(R.id.call);
        detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentorder= new Intent(getApplicationContext(),MapsActivity.class);
                intentorder.putExtra("from",from);
                intentorder.putExtra("to",to);
                startActivity(intentorder);
                Toast.makeText(getApplicationContext(), "my orders",Toast.LENGTH_SHORT).show();
            }
        });



    }
}