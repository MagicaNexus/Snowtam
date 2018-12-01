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
    private ArrayList<TextView> Longitude = new ArrayList<>();
    private ArrayList<TextView> Latitude = new ArrayList<>();
    private ArrayList<TextView> ICAOcode = new ArrayList<>();
    private ArrayList<ConstraintLayout> cl = new ArrayList<>();
    private int visible = View.VISIBLE;
    private int invisible = View.INVISIBLE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        final ConstraintLayout v0 = findViewById(R.id.constraintLayout0);
        final ConstraintLayout v1 = findViewById(R.id.constraintLayout1);
        final ConstraintLayout v2 = findViewById(R.id.constraintLayout2);
        final ConstraintLayout v3 = findViewById(R.id.constraintLayout3);

        cl.add(v0);
        cl.add(v1);
        cl.add(v2);
        cl.add(v3);
        SnowtamDecode truc=new SnowtamDecode();
        String SnowtamDecode="";

      Intent intent = getIntent();
        if (intent != null){
            listAirport = intent.getParcelableArrayListExtra("airports");
        }

        //Données dures
       /* listAirport.add(new Airport("FEML", 49.004476, 2.577238, "snowtam", "Marseille"));
        listAirport.add(new Airport("HYML", 2.577238, 49.004476, "snowtam2", "Londres"));
        listAirport.add(new Airport("QHYL", 45.7484, 4.8467, "snowtam", "Lyon"));
        listAirport.add(new Airport("SZDS", 47.2172, -1.5533, "snowtam", "Nantes"));*/

        //TEXTVIEWS

        final TextView airportName0 = this.findViewById(R.id.airportname0);
        final TextView airportName1 = this.findViewById(R.id.airportname1);
        final TextView airportName2 = this.findViewById(R.id.airportname2);
        final TextView airportName3 = this.findViewById(R.id.airportname3);
        final TextView Longitude0= this.findViewById(R.id.longitude0);
        final TextView Longitude1 = this.findViewById(R.id.longitude1);
        final TextView Longitude2 = this.findViewById(R.id.longitude2);
        final TextView Longitude3 = this.findViewById(R.id.longitude3);
        final TextView Latitude0= this.findViewById(R.id.latitude0);
        final TextView Latitude1 = this.findViewById(R.id.latitude1);
        final TextView Latitude2 = this.findViewById(R.id.latitude2);
        final TextView Latitude3 = this.findViewById(R.id.latitude3);

        airportName.add(airportName0);
        airportName.add(airportName1);
        airportName.add(airportName2);
        airportName.add(airportName3);
        Longitude.add(Longitude0);
        Longitude.add(Longitude1);
        Longitude.add(Longitude2);
        Longitude.add(Longitude3);
        Latitude.add(Latitude0);
        Latitude.add(Latitude1);
        Latitude.add(Latitude2);
        Latitude.add(Latitude3);


        if(listAirport.size() == 1)
        {
            v1.setVisibility(invisible);
            v2.setVisibility(invisible);
            v3.setVisibility(invisible);
        }

        if(listAirport.size() == 2)
        {

            v1.setVisibility(visible);
            v2.setVisibility(invisible);
            v3.setVisibility(invisible);
        }

        if(listAirport.size() == 3)
        {

            v1.setVisibility(visible);
            v2.setVisibility(visible);
            v3.setVisibility(invisible);
        }

        if(listAirport.size() == 4)
        {
            v1.setVisibility(visible);
            v2.setVisibility(visible);
            v3.setVisibility(visible);
        }

        for(int i=0;i<listAirport.size();i++)
        {
            Log.d("AirportLat", "Latitude = " + listAirport.get(i).getLatitude());
            Log.d("AirportLong", "Longitude = " + listAirport.get(i).getLongitude());
            Log.d("AirportName", "Name = " + listAirport.get(i).getName());
            Log.d("AirportIcao", "ICAO = " + listAirport.get(i).getICAO_Code());
            Log.d("AirportSnowtam", "SNOWTAM = " + listAirport.get(i).getSnowtam());
            SnowtamDecode=truc.DecodeSnowtam(listAirport.get(i).getSnowtam(),listAirport.get(i).getName());
            Log.d("AirportSnowtamDecode", "SNOWTAM décodé = " + SnowtamDecode);
            airportName.get(i).setText(listAirport.get(i).getName() +" - " + listAirport.get(i).getICAO_Code());
            Longitude.get(i).setText("Lon. : " +  String.valueOf(listAirport.get(i).getLatitude()));
            Latitude.get(i).setText("Lat. : " + String.valueOf(listAirport.get(i).getLongitude()));

           // Log.d("AirportSnowtam",listAirport.get(i).getSnowtam());

        }

        v0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Results.this.onClick(0);
            }
        });

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Results.this.onClick(1);
            }
        });

        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Results.this.onClick(2);
            }
        });

        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Results.this.onClick(3);
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
                        Results.this.onClick(j);
                    }

                }
            }
        });
    }

    public void onClick(int num)
    {
        /*Intent j = new Intent(Results.this, tabs1.class);
        j.putExtra("index", num);
        j.putExtra("listairport", listAirport);
        startActivity(j);

        Intent i = new Intent(Results.this, MainAirport.class);
        i.putExtra("index", num);
        i.putExtra("listAirport", listAirport);
        startActivity(i);
*/

    }



}
