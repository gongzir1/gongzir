package com.example.sit305quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    EditText editText;
    Context context;
    Toast toast;
    int time=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        time=getIntent().getIntExtra("time",-1);
        String UserName=getIntent().getStringExtra("username");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.button);
        editText = findViewById(R.id.editTextTextPersonName2);
        if(time==1){
            editText.setText(UserName);
        }

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                if (name.equals("")) {
                    context = getApplicationContext();
                    CharSequence text = "Please input your name";
//                    toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
//                    toast.show();
                } else {
                    Intent intent = new Intent(MainActivity.this, Question1_Activity.class);
                    intent.putExtra("username",name);
                    startActivity(intent);
                    MainActivity.this.finish();
                }
            }
        });

        };


}

