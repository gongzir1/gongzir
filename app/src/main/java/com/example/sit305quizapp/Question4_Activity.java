package com.example.sit305quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class Question4_Activity extends AppCompatActivity {

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
    String UserName;
    int score;
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

        UserName=getIntent().getStringExtra("username");
        score=getIntent().getIntExtra("score",-1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        }
                        if (onclick1 == 1) {
                            btn_one.setBackgroundResource(R.drawable.btn_round_red);
                        }
                        onclick1 = 0;
                        onclick2 = 0;
                        onclick3 = 0;

                    }
                    flag=1;
                } else {
                    Intent intent = new Intent(Question4_Activity.this, Question5_Activity.class);
                    intent.putExtra("username",UserName);
                    intent.putExtra("score",score);
                    startActivity(intent);
                    Question4_Activity.this.finish();
                    flag = 0;
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

        btn_one.setText(question.getchoice1(3));
        btn_two.setText(question.getchoice2(3));
        btn_three.setText(question.getchoice3(3));

        answer = question.getCorrectAnswer(3);


        String UserName=getIntent().getStringExtra("username");
        TextView3.setText("Welcome "+UserName+"!");
        TextView4.setText("Android question 4:");
        TextView5.setText(question.getQuestion(3));
        TextView6.setText("4/5");

        //progress bar
        progressBar = findViewById(R.id.hori_progress);
        progressBar.setProgress(80);

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



    public int doWork() {
        index=80;
        return index;
    }
}