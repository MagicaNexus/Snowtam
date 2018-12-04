package com.example.ensim.snowtam;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import Models.AirportModels.Airport;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
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
        mMap.moveCamera(CameraUpdateFactory.newLatLng(airport));
        mMap.setMinZoomPreference(16.0f);
        mMap.setMaxZoomPreference(15.0f);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }


}
