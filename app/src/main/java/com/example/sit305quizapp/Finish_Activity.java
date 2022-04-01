package com.example.sit305quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Finish_Activity extends AppCompatActivity {
    TextView TextView7,TextView8,TextView9;
    Button btn_one,btn_two;
    int time=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        String UserName=getIntent().getStringExtra("username");
        int score=getIntent().getIntExtra("score",-1);
        TextView9=findViewById(R.id.textView9);
        TextView7=findViewById(R.id.textView7);
        TextView8=findViewById(R.id.textView8);
        TextView9.setText("Congratulations "+UserName+"!");
        TextView7.setText("YOUR SCORE: ");
        TextView8.setText(String.valueOf(score)+"/5");
        btn_one = (Button)findViewById(R.id.button5);
        btn_two = (Button)findViewById(R.id.button6);
        Intent intent = new Intent(Finish_Activity.this, MainActivity.class);
        intent.putExtra("username",UserName);
        intent.putExtra("time",time);
        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                Finish_Activity.this.finish();
            }
        });
        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Finish_Activity.this.finish();
            }
        });

    }
}