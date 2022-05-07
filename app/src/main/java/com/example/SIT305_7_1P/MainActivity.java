package com.example.SIT305_7_1P;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //the variables for the buttons
    Button btnShowAlerts, btnCreateAlerts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linking the button variables to the ui elements
        btnCreateAlerts = findViewById(R.id.BtnCreateAlerts);
        btnShowAlerts = findViewById(R.id.BtnShowAlerts);

        //These intents are for the swapping to the other activities
        Intent ShowAlerts = new Intent(this, ShowAlerts.class);
        Intent CreateAlerts= new Intent(this, CreateAlerts.class);

        //these buttons swap to the other activities
        btnCreateAlerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(CreateAlerts);
            }
        });

        btnShowAlerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ShowAlerts);
            }
        });
    }
}