package com.example.restaurantmapapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class AddRestaurantActivity extends AppCompatActivity {
    private static final String TAG = "Running ";
    DatabaseHelper mapDb;
    LocationManager locationManager;
    LocationListener locationListener;
    Button currentLocationButton, showMapButton, saveButton;
    EditText placeNameEditText;
    String placeName;
    Double lng, lat;
    LatLng chosenLocation;

    // Request permissions for location and if granted, retrieve location activating locationListener.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, locationListener);
            }
        }
    }

    // Requests location, asks for permission if not already granted, otherwise retrieves location, activating locationListener.
    public void location() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, locationListener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    // Validate data.
    public Boolean checkPlace(String name, LatLng coords) {
        if (name.equals("Place Name"))
        {
            Toast.makeText(this, "Please enter the location name first.", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (coords == null) {
            Toast.makeText(this, "Please choose a location first.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        currentLocationButton = findViewById(R.id.currentLocationButton);
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        showMapButton = findViewById(R.id.showButton2);
        placeNameEditText = findViewById(R.id.placeNameEditText);
        saveButton = findViewById(R.id.saveButton);
        mapDb = new DatabaseHelper(this);

        // Initialize the SDK
        Places.initialize(getApplicationContext(), getString(R.string.PlacesAPI));

        // Create a new PlacesClient instance
        PlacesClient placesClient = Places.createClient(this);

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        // Set up a PlaceSelectionListener to handle the response. Sets chosenLocation to the places
        // LatLng value.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                chosenLocation = place.getLatLng();
            }

            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        // Current location button retrieves location and sets the Lat/Long in location edittext.
        currentLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location();
            }
        });

        // Checks information provided, passes LatLng and place name of location to be viewed on map.
        showMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeName = placeNameEditText.getText().toString();
                if (checkPlace(placeName, chosenLocation)) {
                    Intent intent = new Intent(AddRestaurantActivity.this, MapsActivity.class);
                    intent.putExtra("SOURCE", "single");
                    intent.putExtra("COORDS", chosenLocation);
                    intent.putExtra("NAME", placeName);
                    startActivity(intent);
                }
            }
        });
        // Checks entry validity, if succeeds, adds data to database and toasts the row ID.
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeName = placeNameEditText.getText().toString();
                if (checkPlace(placeName, chosenLocation)) {
                    long result = mapDb.insertLocation(placeName, chosenLocation.latitude, chosenLocation.longitude);
                }
            }
        });

        // Retrieves the latitude and longitude, sets it to overwrite chosenlocation and sets it
        // to replace the text in the autocomplete. (GPS coords cant be used for places search API).
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                lng = location.getLongitude();
                lat = location.getLatitude();
                chosenLocation = new LatLng(lat, lng);
                autocompleteFragment.setText(lat + ", " + lng);

            }
        };


    }
}