package com.example.sit305quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Question1_Activity extends AppCompatActivity  {


    TextView TextView3,TextView4,TextView5,TextView6;
    ProgressBar progressBar;
    private Handler handler;
    private int index = 0; // 索引
    private int mProgressStaus = 100; // 设置进度条的长度
    private int onclick1=0; //1 -->clicked    0==>unclicked
    private int onclick2=0;
    private int onclick3=0;
    public static int flag = 0;


    Button btn_one, btn_two, btn_three,submit;

    private Question question = new Question();
    private String answer;
    int score=5;

    Random random;


//    private Handler mHandler;
    @SuppressLint({"HandlerLeak", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);
        TextView3=findViewById(R.id.textView3);
        TextView4=findViewById(R.id.textView4);
        TextView5=findViewById(R.id.textView5);
        TextView6=findViewById(R.id.textView6);

        submit=findViewById(R.id.button4);
        String UserName=getIntent().getStringExtra("username");
        TextView3.setText("Welcome "+UserName+"!");
        TextView4.setText("Android question 1:");
        TextView5.setText("Android Studio is based on the IntelliJ IDEA, a _______ integrated development environment for software.");
        TextView6.setText("1/5");

        //progress bar
        progressBar = findViewById(R.id.hori_progress);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score=5;
                if (flag == 0) {
                    submit.setText("Next");
                    if (btn_one.getText() == answer) {
                        btn_one.setBackgroundResource(R.drawable.btn_round_green);
                        if (onclick2 == 1) {
                            btn_two.setBackgroundResource(R.drawable.btn_round_red);
                            score--;
                        }
                        if (onclick3 == 1) {
                            btn_three.setBackgroundResource(R.drawable.btn_round_red);
                            score--;
                        }
                        onclick1 = 0;
                        onclick2 = 0;
                        onclick3 = 0;

                    }
                    if (btn_two.getText() == answer) {
                        btn_two.setBackgroundResource(R.drawable.btn_round_green);
                        if (onclick1 == 1) {
                            btn_one.setBackgroundResource(R.drawable.btn_round_red);
                            score--;
                        }
                        if (onclick3 == 1) {
                            btn_three.setBackgroundResource(R.drawable.btn_round_red);
                            score--;
                        }
                        onclick1 = 0;
                        onclick2 = 0;
                        onclick3 = 0;

                    }
                    if (btn_three.getText() == answer) {
                        btn_three.setBackgroundResource(R.drawable.btn_round_green);
                        if (onclick2 == 1) {
                            btn_two.setBackgroundResource(R.drawable.btn_round_red);
                            score--;
                        }
                        if (onclick1 == 1) {
                            btn_one.setBackgroundResource(R.drawable.btn_round_red);
                            score--;
                        }
                        onclick1 = 0;
                        onclick2 = 0;
                        onclick3 = 0;

                    }
                    flag=1;
                }
               else {
                    Intent intent = new Intent(Question1_Activity.this, Question2_Activity.class);
                    intent.putExtra("username",UserName);
                    intent.putExtra("score",score);
                    startActivity(intent);
                    Question1_Activity.this.finish();
                    flag=0;
                }
            }



        });

        random = new Random();

        btn_one = (Button)findViewById(R.id.button1);
        //btn_one.setOnClickListener(this);
        btn_two = (Button)findViewById(R.id.button2);
        //btn_two.setOnClickListener(this);
        btn_three = (Button)findViewById(R.id.button3);
        //btn_three.setOnClickListener(this);

        btn_one.setText(question.getchoice1(0));
        btn_two.setText(question.getchoice2(0));
        btn_three.setText(question.getchoice3(0));

        answer = question.getCorrectAnswer(0);


    }

    public void buttonClick(View v) {
//        Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
        switch (v.getId()){
            case R.id.button1: {
                onclick1 = 1;
                onclick2=0;
                onclick3=0;
            }
                break;

            case R.id.button2:
            {
                onclick2 = 1;
                onclick1=0;
                onclick3=0;
            }
            break;

            case R.id.button3:
            {
                onclick3 = 1;
                onclick2=0;
                onclick1=0;
            }
            break;


        }
    }



    }
