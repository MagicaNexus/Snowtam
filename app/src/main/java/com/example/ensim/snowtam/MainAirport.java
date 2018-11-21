package com.example.ensim.snowtam;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainAirport extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap mMap;
    private double lon, lat;
    private String snowtam, nameAirport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_airport);

        TextView airportName = findViewById(R.id.mainAirportName);
        TextView longitude = findViewById(R.id.longitude);
        TextView latitude = findViewById(R.id.latitude);

        lat = getIntent().getDoubleExtra("latitude", 0);
        lon = getIntent().getDoubleExtra("longitude",0);
        snowtam = getIntent().getStringExtra("snowtam");
        nameAirport = getIntent().getStringExtra("airportName");

        airportName.setText(nameAirport);
        longitude.setText("" + lon);
        latitude.setText("" + lat);



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
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent i = new Intent(MainAirport.this, MapsActivity.class);
                i.putExtra("longitude", lon);
                i.putExtra("latitude", lat);
                startActivity(i);
            }
        });

    }
}