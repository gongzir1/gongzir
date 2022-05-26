package com.example.trucksharingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trucksharingapp.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    Button signupButton;
    static String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEditText=findViewById(R.id.usernameEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
        loginButton=findViewById(R.id.loginButton);
        signupButton=findViewById(R.id.signupButton);
        db =new DatabaseHelper(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result=db.fetchUser(usernameEditText.getText().toString(),passwordEditText.getText().toString());
                if(result==true)
                {
                    Toast.makeText(MainActivity.this,"Successfully logged in",Toast.LENGTH_SHORT).show();
                    Intent intent1=new Intent(getApplicationContext(),Home.class);
                    user=usernameEditText.getText().toString();
                    intent1.putExtra("user",user);
                    startActivity(intent1);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"No Account",Toast.LENGTH_SHORT).show();
                }
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent= new Intent(MainActivity.this,Sign_up.class);
                startActivity(signUpIntent);
            }
        });
    }
}