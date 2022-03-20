package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    EditText editTextNumber;
    ImageButton clickMetreButton,clickCelButton,clickKgButton;
    TextView TextView1,TextView2,TextView3,TextView4,TextView5,TextView6;
    Toast toast;
    Context context ;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         spinner=findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.my_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.my_array));
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
//        String text = spinner.getSelectedItem().toString();



        //input
        editTextNumber=findViewById(R.id.editTextNumber);
        //button
        clickMetreButton=findViewById(R.id.clickMetreButton);
        clickCelButton=findViewById(R.id.clickCelButton);
        clickKgButton=findViewById(R.id.clickKgButton);

        clickMetreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(spinner.getSelectedItem().toString().equals("Metre")) {
                    Integer a=Integer.parseInt(editTextNumber.getText().toString());
                    Integer b=a*100;
                    double c=a*3.280839895;
                    double d=a*39.37007874;
                    TextView1=findViewById(R.id.textView1);
                    TextView2=findViewById(R.id.textView2);
                    TextView3=findViewById(R.id.textView3);
                    TextView1.setText(String.valueOf(b));
                    TextView2.setText(String.format("%.2f", c));
                    TextView3.setText(String.format("%.2f", d));

                    TextView4=findViewById(R.id.textView4);
                    TextView5=findViewById(R.id.textView5);
                    TextView6=findViewById(R.id.textView6);
                    TextView4.setText("Centimetre");
                    TextView5.setText("Foot");
                    TextView6.setText("Inch");
                }
                else {
                    context = getApplicationContext();
                    CharSequence text = "Please select the correct conversion icon";
                    toast = Toast.makeText(context,text, Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        clickCelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a=Integer.parseInt(editTextNumber.getText().toString());
                double b=a*1.8+32;
                double c=a+273.15;
                if(spinner.getSelectedItem().toString().equals("Celsius")) {
                    TextView1 = findViewById(R.id.textView1);
                    TextView2 = findViewById(R.id.textView2);
                    TextView3 = findViewById(R.id.textView3);
                    TextView1.setText(String.format("%.2f", b));
                    TextView2.setText(String.format("%.2f", c));
                    TextView3.setText("");

                    TextView4 = findViewById(R.id.textView4);
                    TextView5 = findViewById(R.id.textView5);
                    TextView6 = findViewById(R.id.textView6);
                    TextView4.setText("Fahrenheit");
                    TextView5.setText("Kelvin");
                    TextView6.setText("");
                }
                else {
                    context = getApplicationContext();
                    CharSequence text = "Please select the correct conversion icon";
                    toast = Toast.makeText(context,text , Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
        clickKgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinner.getSelectedItem().toString().equals("kilogrammes")) {
                    Integer a = Integer.parseInt(editTextNumber.getText().toString());
                    Integer b = a * 1000;
                    double c = a * 35.273962;
                    double d = a * 2.204623;
                    TextView1 = findViewById(R.id.textView1);
                    TextView2 = findViewById(R.id.textView2);
                    TextView3 = findViewById(R.id.textView3);
                    TextView1.setText(String.valueOf(b));
                    TextView2.setText(String.format("%.2f", c));
                    TextView3.setText(String.format("%.2f", d));

                    TextView4 = findViewById(R.id.textView4);
                    TextView5 = findViewById(R.id.textView5);
                    TextView6 = findViewById(R.id.textView6);
                    TextView4.setText("Gram");
                    TextView5.setText("Ounce(Oz)");
                    TextView6.setText("Pound(lb)");
                }
                else{
                    context = getApplicationContext();
                    CharSequence text = "Please select the correct conversion icon";
                    toast = Toast.makeText(context,text, Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }



//    public void onItemSelected(AdapterView<?> parent, View view,
//                               int pos, long id) {
//        // An item was selected. You can retrieve the selected item using
//        // parent.getItemAtPosition(pos)
//            String selected=parent.getItemAtPosition(pos).toString();
//            Toast.makeText( this,selected,Toast.LENGTH_SHORT).show();




//    public void onNothingSelected(AdapterView<?> parent) {
//        // Another interface callback
//    }
}
