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

public class Order_details extends AppCompatActivity {
    TextView usertv,timetv,goodtypetv,weighttv,heighttv,lengthtv,widthtv;
    ImageView imageView;
    Button callBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Intent intent=getIntent();
        String user="From:"+MainActivity.user;
        String time="Pick up time"+intent.getStringExtra("time");
        String weight="Weight:"+intent.getStringExtra("weight");
        String width="Width"+intent.getStringExtra("width");
        String length="Length"+intent.getStringExtra("length");
        String goodtype="Type:"+intent.getStringExtra("goodtype");
        String height="Height:"+intent.getStringExtra("height");

        imageView=findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.image);
        usertv=findViewById(R.id.Textviewa);
        timetv=findViewById(R.id.Textviewc);
        weighttv=findViewById(R.id.Textviewe);
        widthtv=findViewById(R.id.Textviewg);
        lengthtv=findViewById(R.id.Textviewi);
        goodtypetv=findViewById(R.id.Textviewf);
        heighttv=findViewById(R.id.Textviewh);
        usertv.setText(user);
        timetv.setText(time);
        weighttv.setText(weight);
        widthtv.setText(width);
        lengthtv.setText(length);
        goodtypetv.setText(goodtype);
        heighttv.setText(height);
        callBtn=findViewById(R.id.call);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });



    }
}