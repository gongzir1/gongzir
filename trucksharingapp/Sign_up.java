package com.example.trucksharingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.trucksharingapp.data.DatabaseHelper;
import com.example.trucksharingapp.model.User;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Sign_up extends AppCompatActivity {

    public static final int  SELECT_PHOTO=1;
    public static final int AC_REQ_CODE = 14;
    Uri uri;
    ImageView imageView;
    DatabaseHelper db;
    private byte[] byteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        imageView=findViewById(R.id.imageView);
        EditText sUsernameEditText=findViewById(R.id.et_username2);
        EditText sPasswordEditText=findViewById(R.id.et_password2);
        EditText confirmPasswordEditText=findViewById(R.id.et_cpassword);
        Button saveButton=findViewById(R.id.btn_save);
        db=new DatabaseHelper(this);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageCode;
                String username=sUsernameEditText.getText().toString();
                String password=sPasswordEditText.getText().toString();
                String confirmPassword=confirmPasswordEditText.getText().toString();
                if(imageView.getDrawable()!=null){
                    Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
                    byteArray=stream.toByteArray();
                    imageCode=byteArray.toString();
                    if(password.equals(confirmPassword)&&!username.isEmpty()){
                        if(db.fetchUser(username))
                        {
                            long result=db.insertUser(new User(username,password,imageCode));

                            if(result>0){
                                finish();
                            }}
                        else {Toast.makeText(Sign_up.this,"already have account",Toast.LENGTH_SHORT).show();}


                    }

                }

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==SELECT_PHOTO){
            uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);

                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }
    public void OnClickAdd(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(Sign_up.this);
        builder.setTitle("Alow the app to access photos, media, and files on your device?");
        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "title"), SELECT_PHOTO);
            }
        });
        builder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}