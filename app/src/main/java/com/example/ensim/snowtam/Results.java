package com.example.ensim.snowtam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //Tableau contenant tous les aeroports
        final ArrayList<String> airports = getIntent().getStringArrayListExtra("airports");
        int numberAirport = airports.size(); //Contient le nombre d'aeroport
        Log.d("Nombre d'aeroports : ", String.valueOf(numberAirport));
        Log.d("Nom des aeroports", String.valueOf(airports));


    }
}
