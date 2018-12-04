package com.example.ensim.snowtam;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import Models.AirportModels.Airport;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private UiSettings mUiSettings ;
    private ArrayList<Airport> listAirport;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        listAirport = getIntent().getParcelableArrayListExtra("listAirport");
        index = getIntent().getIntExtra("index",0);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng airport = new LatLng(listAirport.get(index).getLatitude(), listAirport.get(index).getLongitude());
        mMap.addMarker(new MarkerOptions().position(airport).title(listAirport.get(index).getName()));
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(airport,14.0f);
        mMap.animateCamera(location);

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mUiSettings = mMap.getUiSettings();
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMapToolbarEnabled(true);
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setScrollGesturesEnabled(true);
        mUiSettings.setTiltGesturesEnabled(true);
        mUiSettings.setRotateGesturesEnabled(true);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(airport)      // Sets the center of the map to Mountain View
                .zoom(15)                   // Sets the zoom
                .tilt(60)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


}
