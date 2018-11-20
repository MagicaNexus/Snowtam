package com.example.ensim.snowtam;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Results extends AppCompatActivity implements OnMapReadyCallback {
    double lat = 60.29361111111111;
    double lon = 5.218055555555556;
    int nbAirport = 4;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        ConstraintLayout v0 = findViewById(R.id.constraintLayout0);
        ConstraintLayout v1 = findViewById(R.id.constraintLayout1);
        ConstraintLayout v2 = findViewById(R.id.constraintLayout2);
        ConstraintLayout v3 = findViewById(R.id.constraintLayout3);



        final double[][] LocationAirports = {{56.56,58.23},{0,0},{lon,lat},{2.2556262662,58.258741}};
        Log.d("Nombre d'aeroports : ", String.valueOf(LocationAirports));

        if(nbAirport == 1)
        {
           v1.setVisibility(View.GONE);
           v2.setVisibility(View.GONE);
           v3.setVisibility(View.GONE);
        }

        if(nbAirport == 2)
        {
            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.GONE);
            v3.setVisibility(View.GONE);
        }

        if(nbAirport == 3)
        {
            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.VISIBLE);
            v3.setVisibility(View.GONE);
        }

        if(nbAirport == 4)
        {
            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.VISIBLE);
            v3.setVisibility(View.VISIBLE);
        }
        //Tableau contenant tous les aeroports
        //final ArrayList<String> airports = getIntent().getStringArrayListExtra("airports");
        //int numberAirport = airports.size(); //Contient le nombre d'aeroport
        //Log.d("Nombre d'aeroports : ", String.valueOf(numberAirport));
        //Log.d("Nom des aeroports", String.valueOf(airports));



        v0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Results.this, MainAirport.class);
                i.putExtra("longitude", LocationAirports[0][0]);
                i.putExtra("latitude", LocationAirports[0][1]);
                startActivity(i);
            }
        });

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //A recuperer de l'API
                Intent i = new Intent(Results.this, MainAirport.class);
                i.putExtra("longitude", LocationAirports[1][0]);
                i.putExtra("latitude", LocationAirports[1][1]);
                startActivity(i);
            }
        });

        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Results.this, MainAirport.class);
                i.putExtra("longitude", LocationAirports[2][0]);
                i.putExtra("latitude", LocationAirports[2][1]);
                startActivity(i);
            }
        });

        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Results.this, MainAirport.class);
                i.putExtra("longitude", LocationAirports[3][0]);
                i.putExtra("latitude", LocationAirports[3][1]);
                startActivity(i);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapViewResults);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        // Add a marker in Sydney and move the camera
        /*LatLng airport0 = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(airport).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(airport));
        // Add a marker in Sydney and move the camera
        LatLng airport1 = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(airport).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(airport));
        // Add a marker in Sydney and move the camera
        LatLng airport2 = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(airport).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(airport));
        // Add a marker in Sydney and move the camera
        LatLng airport3 = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(airport).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(airport));*/
    }
}
