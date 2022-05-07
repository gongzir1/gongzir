package com.example.SIT305_7_1P;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class ShowAlerts extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_alerts);

        //this is the code for showing the fragments, which contain all the code, as this is part of the
        Fragment fragment = ShowAlertFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FragmentContainer, fragment, "ShowAlertFragment");
        transaction.commit();
    }
}
