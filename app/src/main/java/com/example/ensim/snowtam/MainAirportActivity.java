package com.example.ensim.snowtam;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.ArrayList;

import Model.Airport;
import Model.DetectSwipeGestureListener;
import Model.PageFragment;
import Model.SampleFragmentPagerAdapter;
import Model.SnowtamDecode;
import Model.SnowtamRecuperation;

public class MainAirportActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private int index;
    private ArrayList<Airport> listAirport;
    private GestureDetectorCompat gestureDetectorCompat = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_airport);



        /*GESTURE*/
        DetectSwipeGestureListener gestureListener = new DetectSwipeGestureListener();
        gestureListener.setActivity(this);
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);

        /*TextView*/
        TextView airportName = findViewById(R.id.mainAirportName);
        TextView longitude = findViewById(R.id.longitude);
        TextView latitude = findViewById(R.id.latitude);


        /*Get Intent*/
        listAirport = getIntent().getParcelableArrayListExtra("listAirport");
        index = getIntent().getIntExtra("index",0);

        /*Set Snwotams Raw and Decode*/
        SnowtamRecuperation recup = SnowtamRecuperation.getInstance();
        SnowtamDecode decodage = new SnowtamDecode();
        Log.d("GetSet MainAirport :", "Index : " + recup.getIndex() + " et listAirport" + recup.getListAirport());
        recup.setIndex(index);
        recup.setListAirport(listAirport);



        /*Set Text*/
        airportName.setText(listAirport.get(index).getName());
        longitude.setText("" + listAirport.get(index).getLongitude());
        latitude.setText("" + listAirport.get(index).getLatitude());

        /*Set onglet (je sais pas comment on dit en anglais)*/
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                MainAirportActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        /*Fragment Map*/
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapViewAirport);
        mapFragment.getMapAsync(this);
    }




    //Fonction pour savoir ce qu'on fait en cas de swipe gauche
    public void onSwipeLeft()
    {
        if(index < listAirport.size()-1)
        {
            index++;
            onClick(index, MainAirportActivity.class);
        }
    }

    //Fonction pour savoir ce qu'on fait en cas de swipe droit
    public void onSwipeRight()
    {
        if(index > 0)
        {
            index--;
            onClick(index, MainAirportActivity.class);
        }
    }


    //Fonction pour afficher la MAP
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        // Add a marker in Sydney and move the camera
        final LatLng airport = new LatLng(listAirport.get(index).getLatitude(), listAirport.get(index).getLongitude());
        mMap.addMarker(new MarkerOptions().position(airport).title(listAirport.get(index).getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(airport));
        mMap.setMinZoomPreference(15.0f);
        mMap.setMaxZoomPreference(16.0f);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                onClick(index, MapsActivity.class);
            }
        });

    }

    //Fonction pour le reperage du swipe
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Pass activity on touch event to the gesture detector.
        gestureDetectorCompat.onTouchEvent(event);
        // Return true to tell android OS that event has been consumed, do not pass it to other event listeners.
        return true;
    }

    public void onClick(int num, Class activity)
    {
        Intent i = new Intent(MainAirportActivity.this, activity);
        i.putExtra("index", num);
        i.putExtra("listAirport", listAirport);
        startActivity(i);
        finish();
    }
}