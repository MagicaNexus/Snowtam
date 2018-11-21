package com.example.ensim.snowtam;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import Model.ListAirportLocation;

public enum APIService {
    INSTANCE;

    public void searchLocation(String AirportCode, Response.Listener responseListener, Response.ErrorListener errorListener, Context context){

        final String url="https://v4p4sz5ijk.execute-api.us-east-1.amazonaws.com/anbdata/airports/weather/current-conditions-list?" +
                "api_key=c2ff65c0-ec95-11e8-acf9-1d6bfa3c323d&airports="+AirportCode+"&states=&format=json";

        RequestQueue queue= Volley.newRequestQueue(context);
        GsonRequest<ListAirportLocation> request= new GsonRequest<>(url,ListAirportLocation.class, null, responseListener, errorListener);
        queue.add(request);
    }
}