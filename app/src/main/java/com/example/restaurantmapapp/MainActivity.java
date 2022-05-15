package com.example.restaurantmapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addPlaceButton, showAllButton,showMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPlaceButton = findViewById(R.id.addButton);
        showMapButton = findViewById(R.id.showButton);
        showAllButton=findViewById(R.id.showAllButton);
        // Opens add restaurant activity on click.
        addPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });

        // Opens map and shows all locations saved in the database.
        showMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("SOURCE", "all");
                startActivity(intent);
            }
        });
        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowAllItemActivity.class);
                intent.putExtra("SOURCE", "all");
                startActivity(intent);
            }
        });
    }

}