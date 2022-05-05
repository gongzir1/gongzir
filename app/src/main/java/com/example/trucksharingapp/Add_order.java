package com.example.trucksharingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

public class Add_order extends AppCompatActivity {
    CalendarView simpleCalendarView;
    Button nextPagebtn;
    String Date;
    String Time;
    String Location;
    EditText timeEt;
    EditText LocationEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView); // get the reference of CalendarView
        simpleCalendarView.setFocusedMonthDateColor(Color.RED); // set the red color for the dates of  focused month
        simpleCalendarView.setUnfocusedMonthDateColor(Color.BLUE); // set the yellow color for the dates of an unfocused month
        simpleCalendarView.setSelectedWeekBackgroundColor(Color.RED); // Thiết lập màu đỏ cho các tuần, Từ API 23 trở lên mới hỗ trợ
        simpleCalendarView.setWeekSeparatorLineColor(Color.GREEN); // Thiết lập cho đường khoảng cách giữa các tuần là màu xanh
        // perform setOnDateChangeListener event on CalendarView
        nextPagebtn=findViewById(R.id.btn_next);
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                Date=String.valueOf(dayOfMonth)+"/"+month+"/"+year;
            }
        });

        nextPagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeEt=findViewById(R.id.et_time);
                LocationEt=findViewById(R.id.et_location);
                Time=timeEt.getText().toString();
                Location=LocationEt.getText().toString();


                if(Date==null||TextUtils.isEmpty(timeEt.getText().toString())||TextUtils.isEmpty(LocationEt.getText().toString())){
                    Toast.makeText(Add_order.this,"PLease fill all detail",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent=new Intent(Add_order.this,add_new_delivery_order.class);
                    intent.putExtra("date",Date);
                    intent.putExtra("time",Time);
                    intent.putExtra("location",Location);
                    startActivity(intent);
                }

            }
        });

    }
}