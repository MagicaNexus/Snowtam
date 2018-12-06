package com.example.ensim.snowtam;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Models.AirportModels.Airport;
import Models.SnowtamModels.SnowtamDecode;
import Models.SnowtamModels.SnowtamSingleton;

public class ResultsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private UiSettings mUiSettings;
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
        final ImageButton add1=findViewById(R.id.add1);
        final ImageButton add2=findViewById(R.id.add2);
        final ImageButton add3=findViewById(R.id.add3);
        add1.setVisibility(View.GONE);
        add2.setVisibility(View.GONE);
        add3.setVisibility(View.GONE);
        final ImageButton go0=findViewById(R.id.go0);
        final ImageButton go1=findViewById(R.id.go1);
        final ImageButton go2=findViewById(R.id.go2);
        final ImageButton go3=findViewById(R.id.go3);

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


        DecimalFormat df = new DecimalFormat("########.0000000");
        for(int i=0;i<listAirport.size();i++)
        {
            Log.d("AirportLat", "Latitude = " + listAirport.get(i).getLatitude());
            Log.d("AirportLong", "Longitude = " + listAirport.get(i).getLongitude());
            Log.d("AirportName", "Name = " + listAirport.get(i).getName());
            Log.d("AirportIcao", "ICAO = " + listAirport.get(i).getICAO_Code());
            Log.d("AirportSnowtam", "SNOWTAM = " + listAirport.get(i).getSnowtam());
            SnowtamDecode=truc.DecodeSnowtam(listAirport.get(i).getSnowtam(),listAirport.get(i).getName());
            Log.d("AirportSnowtamDecode", "SNOWTAM décodé = " + SnowtamDecode);
            airportName.get(i).setText(listAirport.get(i).getName() +" \n " + listAirport.get(i).getICAO_Code());
            Longitude.get(i).setText("Lon. : " +  String.valueOf(df.format(listAirport.get(i).getLatitude())));
            Latitude.get(i).setText("Lat. : " + String.valueOf(df.format(listAirport.get(i).getLongitude())));

            // Log.d("AirportSnowtam",listAirport.get(i).getSnowtam());

        }

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultsActivity.this.onClickAdd(listAirport);
            }
        });
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultsActivity.this.onClickAdd(listAirport);
            }
        });
        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultsActivity.this.onClickAdd(listAirport);
            }
        });

        if(listAirport.size() == 1)
        {
            add1.setVisibility(visible);
            add2.setVisibility(visible);
            add3.setVisibility(visible);
            go1.setVisibility(View.GONE);
            go2.setVisibility(View.GONE);
            go3.setVisibility(View.GONE);
        }

        if(listAirport.size() == 2)
        {

            add1.setVisibility(View.GONE);
            add2.setVisibility(visible);
            add3.setVisibility(visible);
            go1.setVisibility(visible);
            go2.setVisibility(View.GONE);
            go3.setVisibility(View.GONE);
        }

        if(listAirport.size() == 3)
        {

            add1.setVisibility(View.GONE);
            add2.setVisibility(View.GONE);
            add3.setVisibility(visible);
            go1.setVisibility(visible);
            go2.setVisibility(visible);
            go3.setVisibility(View.GONE);
        }

        if(listAirport.size() == 4)
        {
            add1.setVisibility(View.GONE);
            add2.setVisibility(View.GONE);
            add3.setVisibility(View.GONE);
            go1.setVisibility(visible);
            go2.setVisibility(visible);
            go3.setVisibility(visible);
        }



        v0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultsActivity.this.onClick(0);
            }
        });

        if(add1.getVisibility()!=View.VISIBLE) {
            v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResultsActivity.this.onClick(1);
                }
            });
        }
        if(add2.getVisibility()!=View.VISIBLE) {
            v2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResultsActivity.this.onClick(2);
                }
            });
        }
        if(add3.getVisibility()!=View.VISIBLE) {
            v3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ResultsActivity.this.onClick(3);
                }
            });
        }

        go0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultsActivity.this.onClick(0);
            }
        });

        go1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultsActivity.this.onClick(1);
            }
        });

        go2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultsActivity.this.onClick(2);
            }
        });

        go3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultsActivity.this.onClick(3);
            }
        });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapViewResults);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        PolylineOptions rectOptions = new PolylineOptions();
        final ArrayList<LatLng> airport = new ArrayList<>();
        for(int j=0;j<listAirport.size();j++)
        {
            airport.add(new LatLng(listAirport.get(j).getLatitude(),listAirport.get(j).getLongitude()));
            mMap.addMarker(new MarkerOptions().position(airport.get(j)).title(listAirport.get(j).getName() + " airport | " + listAirport.get(j).getCityName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(airport.get(j)));
            if(listAirport.size()==2)
                rectOptions.add(new LatLng(listAirport.get(j).getLatitude(),listAirport.get(j).getLongitude()));
        }
        Polyline polyline = mMap.addPolyline(rectOptions
                .color(Color.RED)
                .geodesic(true)
                .width(5));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        if(listAirport.size() == 1) {
            CameraUpdate location = CameraUpdateFactory.newLatLngZoom(airport.get(0),10);
            mMap.animateCamera(location);
        }
        if(listAirport.size() == 2)
        {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(airport.get(1), 15));
            map.animateCamera(CameraUpdateFactory.zoomIn());
            map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(airport.get(0))      // Sets the center of the map to Mountain View
                    .zoom(7)                   // Sets the zoom
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        else
        {
            mMap.animateCamera(CameraUpdateFactory.newLatLng(airport.get(0)));
        }
        mUiSettings = mMap.getUiSettings();
        mUiSettings.setCompassEnabled(false);
        mUiSettings.setMapToolbarEnabled(false);
        mUiSettings.setZoomControlsEnabled(false);
        mUiSettings.setScrollGesturesEnabled(true);
        mUiSettings.setTiltGesturesEnabled(true);
        mUiSettings.setRotateGesturesEnabled(true);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                for(int j=0;j<listAirport.size();j++){

                    if(marker.getTitle().equals(listAirport.get(j).getName()))
                    {
                        Log.d("Marker :", marker.getTitle() + " =? " + listAirport.get(j).getName());
                        ResultsActivity.this.onClick(j);
                    }

                }
            }
        });

    }

    public void onClick(int num)
    {
        Intent i = new Intent(ResultsActivity.this, MainAirportActivity.class);
        SnowtamSingleton recup = SnowtamSingleton.getInstance();
    //    recup.setIndex(num);
      //  recup.setListAirport(listAirport);
        i.putExtra("index", num);
        i.putExtra("listAirport", this.listAirport);
        startActivity(i);

    }

    public void onClickAdd(ArrayList<Airport> listAirport)
    {
        Intent j = new Intent(ResultsActivity.this, AccueilActivity.class);
        j.putExtra("listAirport", this.listAirport);
        startActivity(j);

    }


}
