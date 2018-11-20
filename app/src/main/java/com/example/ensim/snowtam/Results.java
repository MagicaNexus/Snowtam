package com.example.ensim.snowtam;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;

public class Results extends AppCompatActivity implements OnMapReadyCallback {
    double lat = 60.29361111111111;
    double lon = 5.218055555555556;
    int nbAirport = 4;
    final double[][] LocationAirports = {{56.56,58.23},{0,0},{lon,lat},{2.2556262662,58.258741}};
    private GoogleMap mMap;
    private ArrayList<Airport> listAirport = new ArrayList<Airport>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        ConstraintLayout v0 = findViewById(R.id.constraintLayout0);
        ConstraintLayout v1 = findViewById(R.id.constraintLayout1);
        ConstraintLayout v2 = findViewById(R.id.constraintLayout2);
        ConstraintLayout v3 = findViewById(R.id.constraintLayout3);

        Intent intent = getIntent();
        if (intent != null){
            listAirport = intent.getParcelableArrayListExtra("airports");
        }

        for(int i=0;i<listAirport.size();i++){
            Log.d("AirportLat", "Latitude = " + listAirport.get(i).getLatitude());
            Log.d("AirportLong", "Longitude = " + listAirport.get(i).getLongitude());
            Log.d("AirportName", "Name = " + listAirport.get(i).getName());
        }




        /*
        !!!!!!!!!!!********Pour monsieur Matthieu RODE********!!!!!!!!!!!!!!!!!!!!
        listAirport correspond a une liste qui contient les aeroports entrés dans la premiere page
        si tu veux la longitude du premier aeroport de la liste tu fais:
        listAirport.get(0).getLongitude()
        et latitude:
        listAirport.get(0).getLatitude()
        Et si tu veux son nom tu fais:
        listAirport.get(0).getName()
        */



        Log.d("Nombre d'aeroports : ", String.valueOf(listAirport.size()));

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
                i.putExtra("longitude", listAirport.get(0).getLongitude());
                i.putExtra("latitude", listAirport.get(0).getLatitude());
                startActivity(i);
            }
        });

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //A recuperer de l'API
                Intent i = new Intent(Results.this, MainAirport.class);
                i.putExtra("longitude", listAirport.get(1).getLongitude());
                i.putExtra("latitude", listAirport.get(1).getLatitude());
                startActivity(i);
            }
        });

        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Results.this, MainAirport.class);
                i.putExtra("longitude", listAirport.get(2).getLongitude());
                i.putExtra("latitude", listAirport.get(2).getLatitude());
                startActivity(i);
            }
        });

        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Results.this, MainAirport.class);
                i.putExtra("longitude", listAirport.get(3).getLongitude());
                i.putExtra("latitude", listAirport.get(3).getLatitude());
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
        ArrayList<LatLng> airport = new ArrayList<>();
        for(int j=0;j<nbAirport;j++)
        {
            airport.add(new LatLng(LocationAirports[j][0],LocationAirports[j][1]));
            mMap.addMarker(new MarkerOptions().position(airport.get(j)).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(airport.get(j)));
        }



    }
}
