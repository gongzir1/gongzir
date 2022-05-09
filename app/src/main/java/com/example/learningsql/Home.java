package com.example.learningsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.learningsql.data.DatabaseHelper;
import com.example.learningsql.model.YoutubeLink;

public class Home extends AppCompatActivity {
    EditText linkTv;
    Button playBtn,addBtn,openListBtn;
    String link;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        linkTv=findViewById(R.id.link_et);
        playBtn=findViewById(R.id.button);
        addBtn=findViewById(R.id.button2);
        openListBtn=findViewById(R.id.button3);
        db=new DatabaseHelper(this);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, PlayVideoActivity.class);
                String a=linkTv.getText().toString();


                Log.d("please",linkTv.getText().toString().replace("https://www.youtube.com/watch?v=",""));
                intent.putExtra("url", linkTv.getText().toString().replace("https://www.youtube.com/watch?v=",""));

                startActivity(intent);
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link=linkTv.getText().toString();
                if(!link.isEmpty()){
                    YoutubeLink videoLink=new YoutubeLink();
                    videoLink.setLink(link);
                    videoLink.setUser(MainActivity.username);
                    db.insertLink(videoLink);

                }
            }
        });
        openListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),linkList.class);
                startActivity(intent);
            }
        });

    }
}