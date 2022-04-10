package com.example.timer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Chronometer timer;
    private long pauseOffset;
    private boolean running;
    EditText taskType;
    TextView show,textview;
    SharedPreferences sharedPreferences;
    String taskName;
    String time;
    int timeInt;
    String TIME, RUNNING;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show=findViewById(R.id.textView3);
        textview=findViewById(R.id.textView2);

        timer = findViewById(R.id.chronometer);
        taskType=findViewById(R.id.editTextTaskType);
        time= timer.getText().toString();

        taskName=taskType.getText().toString();
        sharedPreferences=getSharedPreferences("com.example.timer",MODE_PRIVATE);
        checkSharedPreferences();
        timeInt=0;

        if(savedInstanceState !=null){

            String time=savedInstanceState.getString(TIME);

//            running=savedInstanceState.getBoolean(RUNNING,false);
            //textview.setText(String.valueOf(running));
             timeInt = getChronometerInt(time);
             timer.setBase(SystemClock.elapsedRealtime() - timeInt * 1000);
             timer.start();

            sharedPreferences = getSharedPreferences("com.example.timer", MODE_PRIVATE);
            checkSharedPreferences();
        }



    }
    public  int getChronometerInt(String time) {


            String[] split = time.split(":");
            String string3 = split[0];
            int min = Integer.parseInt(string3);
            int Mins =min*60;
            int  SS =Integer.parseInt(split[1]);

            int totalss =Mins+SS;
            return totalss;

    }




    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //time=timer.getText().toString();
//        outState.putBoolean(RUNNING,running);
        outState.putString(TIME,timer.getText().toString());




    }

    public void checkSharedPreferences() {
        String taskType=sharedPreferences.getString(taskName,"...");
        String gettime=sharedPreferences.getString(time,"");
        show.setText("You spent "+gettime+" on "+taskType+" last time");

    }


    public void startChronometer(View v) {
        if (!running) {
            timer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            timer.start();
            running = true;

        }

    }

    public void pauseChronometer(View v) {
        if (running) {
            timer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - timer.getBase();
            running = false;

        }
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(taskName,taskType.getText().toString());
        editor.putString(time,timer.getText().toString());
        editor.apply();
    }

    public void resetChronometer(View v) {
        timer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }






}


