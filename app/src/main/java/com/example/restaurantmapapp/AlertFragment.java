package com.example.restaurantmapapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AlertFragment extends Fragment {

    private static Alert AlertItem;

    public AlertFragment() {
        // Required empty public constructor
    }

    //this method creates and returns a new instance of the fragment
    public static AlertFragment newInstance(Alert alertItem) {
        AlertFragment fragment = new AlertFragment();
        AlertItem = alertItem;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //this inflater instantiates the layout xml
        View view = inflater.inflate(R.layout.fragment_alert, container, false);

        //these are the variables used to refer to the ui elements and are being linked to the ui elements themselves
        TextView LabelAlertNameAndType = view.findViewById(R.id.LabelAlertNameAndType);
        TextView LabelAlertPhone = view.findViewById(R.id.LabelAlertPhone);
        TextView LabelAlertDescription = view.findViewById(R.id.LabelAlertDescription);
        TextView LabelAlertDate = view.findViewById(R.id.LabelAlertDate);
        TextView LabelAlertLocation = view.findViewById(R.id.LabelAlertLocation);
        Button ButtonRemove = view.findViewById(R.id.ButtonRemove);

        //these set the text on the labels so that the ui elements can display the relevant alert
        LabelAlertNameAndType.setText(AlertItem.getName()+" "+AlertItem.getLostOrFound()+" an item.");
        LabelAlertPhone.setText(String.valueOf(AlertItem.getPhone()));
        LabelAlertDescription.setText(AlertItem.getDescription());
        LabelAlertDate.setText(AlertItem.getDate());
        LabelAlertLocation.setText(AlertItem.getLocation());

        //this creates the class that connects the program to the database
        DatabaseHelper db = new DatabaseHelper(getContext());

        //this is the onclick event for the remove button
        ButtonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this line deletes the alert being shown in the fragment from the database
                db.DeleteAlert(AlertItem);

                //this section replaces the current fragment with the show alert fragment after the alert is deleted
                Fragment fragment = ShowAlertFragment.newInstance();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.hide(getActivity().getSupportFragmentManager().findFragmentByTag("AlertFragment"));
                transaction.add(R.id.FragmentContainer, fragment,"ShowAlertFragment");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}