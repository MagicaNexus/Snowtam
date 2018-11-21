package com.example.ensim.snowtam;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Results extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ArrayList<Airport> listAirport = new ArrayList<>();
    private ArrayList<TextView> airportName = new ArrayList<>();
    private ArrayList<TextView> GPS = new ArrayList<>();
    private ArrayList<TextView> ICAOcode = new ArrayList<>();



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

        //Données dures
        /*listAirport.add(new Airport("FEML", 49.004476, 2.577238, "snowtam", "Paris"));
        listAirport.add(new Airport("HYML", 2.577238, 49.004476, "snowtam2", "Londres"));
        listAirport.add(new Airport("QHYL", 45.7484, 4.8467, "snowtam", "Lyon"));
        listAirport.add(new Airport("SZDS", 47.2172, -1.5533, "snowtam", "Nantes"));*/

        //TEXTVIEWS

        final TextView airportName0 = this.findViewById(R.id.airportname0);
        final TextView airportName1 = this.findViewById(R.id.airportName1);
        final TextView airportName2 = this.findViewById(R.id.airportname2);
        final TextView airportName3 = this.findViewById(R.id.airportname3);
        final TextView GPS0= this.findViewById(R.id.GPSLocation0);
        final TextView GPS1 = this.findViewById(R.id.GPSLocation1);
        final TextView GPS2 = this.findViewById(R.id.GPSLocation2);
        final TextView GPS3 = this.findViewById(R.id.GPSLocation3);
        final TextView MAJTime0 = this.findViewById(R.id.updateTime0);
        final TextView MAJTime1 = this.findViewById(R.id.updateTime1);
        final TextView MAJTime2 = this.findViewById(R.id.updateTime2);
        final TextView MAJTime3 = this.findViewById(R.id.updateTime3);

        airportName.add(airportName0);
        airportName.add(airportName1);
        airportName.add(airportName2);
        airportName.add(airportName3);
        GPS.add(GPS0);
        GPS.add(GPS1);
        GPS.add(GPS2);
        GPS.add(GPS3);
        ICAOcode.add(MAJTime0);
        ICAOcode.add(MAJTime1);
        ICAOcode.add(MAJTime2);
        ICAOcode.add(MAJTime3);





        if(listAirport.size() == 1)
        {
            v1.setVisibility(View.INVISIBLE);
            v2.setVisibility(View.INVISIBLE);
            v3.setVisibility(View.INVISIBLE);
        }

        if(listAirport.size() == 2)
        {

            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.INVISIBLE);
            v3.setVisibility(View.INVISIBLE);
        }

        if(listAirport.size() == 3)
        {

            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.VISIBLE);
            v3.setVisibility(View.INVISIBLE);
        }

        if(listAirport.size() == 4)
        {
            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.VISIBLE);
            v3.setVisibility(View.VISIBLE);
        }

        for(int i=0;i<listAirport.size();i++)
        {
            Log.d("AirportLat", "Latitude = " + listAirport.get(i).getLatitude());
            Log.d("AirportLong", "Longitude = " + listAirport.get(i).getLongitude());
            Log.d("AirportName", "Name = " + listAirport.get(i).getName());
            airportName.get(i).setText(listAirport.get(i).getName());
            GPS.get(i).setText(listAirport.get(i).getLatitude() + "/" + listAirport.get(0).getLongitude());
            ICAOcode.get(i).setText(listAirport.get(i).getICAO_Code());

        }

        v0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Results.this, MainAirport.class);
                i.putExtra("index", 0);
                i.putExtra("listAirport", listAirport);
                startActivity(i);
            }
        });

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Results.this, MainAirport.class);
                i.putExtra("index", 1);
                i.putExtra("listAirport", listAirport);
                startActivity(i);
            }
        });

        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Results.this, MainAirport.class);
                i.putExtra("index", 2);
                i.putExtra("listAirport", listAirport);
                startActivity(i);
            }
        });

        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Results.this, MainAirport.class);
                i.putExtra("index", 3);
                i.putExtra("listAirport", listAirport);
                startActivity(i);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapViewResults);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        final ArrayList<LatLng> airport = new ArrayList<>();
        for(int j=0;j<listAirport.size();j++)
        {
            airport.add(new LatLng(listAirport.get(j).getLatitude(),listAirport.get(j).getLongitude()));
            mMap.addMarker(new MarkerOptions().position(airport.get(j)).title(listAirport.get(j).getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(airport.get(j)));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(airport.get(0)));
        mMap.setMinZoomPreference(5.0f);
        mMap.setMaxZoomPreference(15.0f);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                for(int j=0;j<listAirport.size();j++){

                    if(marker.getTitle().equals(listAirport.get(j).getName()))
                    {
                        Log.d("Marker :", marker.getTitle() + " =? " + listAirport.get(j).getName());
                        Intent i = new Intent(Results.this, MainAirport.class);
                        i.putExtra("index", j);
                        i.putExtra("listAirport", listAirport);
                        startActivity(i);
                    }

                }
            }
        });
    }
}
