package com.example.SIT305_7_1P;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ShowAlertFragment extends Fragment implements AlertsAdapter.ItemClickListener {

    //variable for storing the list of alerts
    ArrayList<Alert> AlertsList = new ArrayList<>();

    public ShowAlertFragment() {
        // Required empty public constructor
    }

    //method that creates and returns a show alert fragment
    public static ShowAlertFragment newInstance() {
        ShowAlertFragment fragment = new ShowAlertFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //this inflater instantiates the layout xml
        View view = inflater.inflate(R.layout.fragment_show_alert, container, false);

        //create the class that will connect to the database and populate the arraylist with the alerts from the database
        Database db = new Database(getContext());
        AlertsList = db.fetchAllAlerts();

        //create the recyclerview, link it to the ui element, and assign the layout manager and adapter
        RecyclerView RecyclerViewAlerts = view.findViewById(R.id.RecyclerViewAlerts);
        LinearLayoutManager VerticalManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        RecyclerViewAlerts.setLayoutManager(VerticalManager);
        AlertsAdapter AlertAdapter = new AlertsAdapter(getContext(), AlertsList, this);
        RecyclerViewAlerts.setAdapter(AlertAdapter);

        return view;
    }

    @Override
    public void onItemClick(Alert alert) {
        //this is part of the code taken from the video that opens a fragment when clicking an item in the recycler view
        Fragment fragment = AlertFragment.newInstance(alert);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.hide(getActivity().getSupportFragmentManager().findFragmentByTag("ShowAlertFragment"));
        transaction.add(R.id.FragmentContainer, fragment, "AlertFragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}