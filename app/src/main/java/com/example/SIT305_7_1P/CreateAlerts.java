package com.example.SIT305_7_1P;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAlerts extends AppCompatActivity {

    //these are the variables for the ui elements
    TextView textboxName, textboxPhone, textboxDescription, textboxDate, textboxLocation;
    RadioButton RDBLost, RDBFound;
    Button btnSaveAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alerts);

        //this creates an instance of the database class, which connects the program to the database
        Database db = new Database(this);

        //these link the variables to their ui elements
        textboxName = findViewById(R.id.TextboxName);
        textboxPhone = findViewById(R.id.TextboxPhone);
        textboxDescription = findViewById(R.id.TextboxDescription);
        textboxDate = findViewById(R.id.TextboxDate);
        textboxLocation = findViewById(R.id.TextboxLocation);
        RDBLost = findViewById(R.id.rdbLost);
        RDBFound = findViewById(R.id.rdbFound);
        btnSaveAlert = findViewById(R.id.BtnSaveAlert);

        //The intent for facilitating activity swapping
        Intent ReturnMain = new Intent(this, MainActivity.class);

        //This button checks the validity of the inputs, saves the alert to the database, then changes beck to the main activity
        btnSaveAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these are the variables for the data
                String Type = "";
                String Name = textboxName.getText().toString();
                int Phone = 0;
                String Description = textboxDescription.getText().toString();
                String Date = textboxDate.getText().toString();
                String Location = textboxLocation.getText().toString();
                //The boolean is to ensure the user entered data in all the fields
                boolean input = true;
                //These check the textboxes and radiobuttons to ensure the user has input all the required data.
                //If they are not filled out properly, the boolean is turned to false. It also fills out the lost/found status
                if (RDBLost.isChecked() == true){
                    Type = "Lost";
                }else if (RDBFound.isChecked() == true){
                    Type = "Found";
                }else{
                    input = false;
                }
                if (!textboxPhone.getText().toString().equals("")){
                    Phone = Integer.parseInt(textboxPhone.getText().toString());
                }else{
                    input = false;
                }
                if (textboxName.getText().toString().equals("")||textboxDescription.getText().toString().equals("")
                        ||textboxDate.getText().toString().equals("")||textboxLocation.getText().toString().equals("")){
                    input = false;
                }
                Alert TempAlert;
                //This if statement is the second part of the validation, as it value of the boolean and
                //either saves the alert to the database or informs the user that they need to fill out the fields
                if (input == true){
                    //this populates the tempAlert with the relevant info and adds it to the database,
                    //then returns the user to the main activity
                    TempAlert = new Alert(Type,Name,Phone,Description,Date,Location);
                    db.InsertAlert(TempAlert);
                    startActivity(ReturnMain);
                }else{
                    //This shows a popup that informs the user that they did not properly fill in the fields
                    Toast.makeText(getApplicationContext(), "The textboxes/radiobuttons should not be empty.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}