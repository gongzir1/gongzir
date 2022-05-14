package com.example.restaurantmapapp;

import androidx.fragment.app.FragmentActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.restaurantmapapp.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    DatabaseHelper mapDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mapDb = new DatabaseHelper(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // If from single location view, mark only the single location.
        if (this.getIntent().getStringExtra("SOURCE").equals("single")) {
            LatLng coords = this.getIntent().getParcelableExtra("COORDS");
            String placeName = this.getIntent().getStringExtra("NAME");
            mMap.addMarker(new MarkerOptions().position(coords).title(placeName));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coords, 10));
        }
        // Load all saved in database and move camera accordingly.
        else {
            try {
                loadSaved(mMap);
            }
            catch (IllegalStateException illegalStateException) {
                Toast.makeText(this, "No locations saved to show.", Toast.LENGTH_LONG).show();
            }
        }
    }

    // Iterate through all saved map data in database. Add all locations as markers and create a
    // LatLngBounds to focus the camera with.
    public void loadSaved(GoogleMap map) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Cursor cursor = mapDb.fetchAllMapData();

        while (cursor.moveToNext()) {
            LatLng temporary = new LatLng(cursor.getDouble(2), cursor.getDouble(3));
            builder.include(temporary);
            map.addMarker(new MarkerOptions().position(temporary).title(cursor.getString(1)));
        }
        LatLngBounds bounds = builder.build();
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 300));
    }
}