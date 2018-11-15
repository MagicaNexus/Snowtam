package com.example.ensim.snowtam;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainAirport extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap mMap;
    private int lon = 2, lat = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_airport);
        TextView airportName = findViewById(R.id.textViewName);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapViewAirport);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        // Add a marker in Sydney and move the camera
        LatLng airport = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(airport).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(airport));
    }
}